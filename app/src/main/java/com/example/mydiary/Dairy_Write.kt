package com.example.mydiary

import android.R.attr.defaultValue
import android.R.attr.keySet
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_dairy_write.*
import kotlinx.android.synthetic.main.fragment_dairy_write.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


var diaryText: String? = null
var diaryTitle: String? = null
var diaryDate: String? = null

class Dairy_Write : Fragment() {

    lateinit var LocalRepositoryImp: LocalRepositoryImp
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

//        val bundle = this.arguments
//        if (bundle != null) {
//            diaryText = arguments?.getString("tim")
//
//            diaryTitle = arguments?.getString("tit")
//            diaryDate = arguments?.getString("dr")
//
//
//
//
//
//        }






    }

    //    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,


        ): View? {


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dairy_write, container, false)


        var db = diaryDataBase.getInstance(requireContext())

        LocalRepositoryImp = LocalRepositoryImp(db)

//    view.ed_title.setText(diaryText)
//    view.ed_diary.setText(diaryTitle)


        view.ed_title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
          view.save.isEnabled = p0.toString().trim().isNotEmpty()


//                if(p0==null){
//                    save.isEnabled=false
//                }
//                else{
//                    save.isEnabled=true
//                }
             }

            override fun afterTextChanged(p0: Editable?) {


            }

        })

           view.ed_title.setText(diaryTitle)
            view.ed_diary.setText(diaryText)




        var title = view.ed_title.text
        var text = view.ed_diary.text
        var time = LocalDateTime.now()
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

        var t:String
        if (diaryDate!=null)
        {
            t=diaryDate.toString()
        }
        else{


            t=time.format(formatter)
        }


        view.save.setOnClickListener {


            GlobalScope.launch(Dispatchers.IO) {

               LocalRepositoryImp.deleteDiary(
                   Dairy(diaryText.toString(), diaryTitle.toString(), t)
               )
                LocalRepositoryImp.addDiary(
                    Dairy(text.toString(), title.toString(), t)
                )

            }
            Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.diary_list4)



        }






















        return view
    }




}






