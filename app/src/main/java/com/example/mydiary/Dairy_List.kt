package com.example.mydiary

import android.content.ClipData
import android.content.Context
import android.os.Bundle
import android.os.Process
import android.view.KeyboardShortcutGroup
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_dairy_list.*
import kotlinx.android.synthetic.main.fragment_dairy_list.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
//var diaryText2: String? = null
//var diaryTitle2: String? = null
//var diaryDate2: String? = null

public class Dairy_List : Fragment() {



     var data=mutableListOf<Dairy>()

    private lateinit var LocalRepositoryImp:LocalRepositoryImp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        fun View.hideKeyboard() {
            val imm = view?.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }




//        diaryTitle=null


        var db=diaryDataBase.getInstance(requireContext())

        LocalRepositoryImp= LocalRepositoryImp(db)


        getalll()


        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_dairy_list, container, false)
        view.FB.setOnClickListener{
            diaryTitle=null
            diaryDate=null
            diaryText=null
            Navigation.findNavController(view).navigate(R.id.diary_write)
        }

        val recyclerview =view.rc


        recyclerview.layoutManager = LinearLayoutManager(requireContext())












           data= getalll() as MutableList<Dairy>


        val adapter = customAdapter(data)

        recyclerview.adapter = adapter


        getalll()





        //bach mayrja3ch l write
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    val pid = Process.myPid()
                    Process.killProcess(pid)                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            callback
        )



        return view
    }

    override fun onResume() {
        getalll()
        super.onResume()
        getalll()


    }

    override fun onPause() {
        getalll()
        super.onPause()
        getalll()
    }

    override fun onStart() {
        getalll()
        super.onStart()
        getalll()
    }

    fun getalll(): MutableList<Dairy> {

        GlobalScope.launch(Dispatchers.IO) {
            var returnedDairies=async {

                LocalRepositoryImp.getALL()
            }

            data= returnedDairies.await() as MutableList<Dairy>


        }
        return data
    }



}
