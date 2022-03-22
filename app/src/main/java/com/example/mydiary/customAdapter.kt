package com.example.mydiary

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class customAdapter(private val dataSet: List<Dairy>) :
    RecyclerView.Adapter<customAdapter.ViewHolder>() {


    private lateinit var LocalRepositoryImp:LocalRepositoryImp
lateinit var tim:String
    lateinit var tit:String
    lateinit var dr:String

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener {
        val title: TextView
        val time: TextView
        val text:TextView
        val image:ImageButton




        init {
            // Define click listener for the ViewHolder's View.
            title = view.findViewById(R.id.tText)
            time = view.findViewById(R.id.tTime)
            text=view.findViewById(R.id.diary)
            image=view.findViewById(R.id.imagebtn)

//               fun dd (position: Int): CharSequence? {
//                    return dtext.dtext
//                }

            view.setOnClickListener (this)
        }

        override fun onClick(p0: View?) {

//            Toast.makeText(itemView.context, "ok", Toast.LENGTH_SHORT).show()









        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)

        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int){
       val x=dataSet[position]
        holder.title.text=x.title.trim(' ')
        holder.time.text=x.time.trim(' ')
        holder.text.text=x.text.trim(' ')
        tim=x.time.trim(' ')
        tit=x.title.trim(' ')
        dr=x.text.trim(' ')


        holder.title.setOnClickListener(){

                diaryTitle=holder.title.text.toString()
                diaryDate=holder.time.text.toString()
                diaryText=holder.text.text.toString()




//              diaryText=dr
            findNavController(holder.itemView)?.navigate(R.id.diary_write)

        }
        holder.image.setOnClickListener(){
            var db=diaryDataBase.getInstance(holder.itemView.context)

            LocalRepositoryImp= LocalRepositoryImp(db)


            GlobalScope.launch(Dispatchers.IO) {

                LocalRepositoryImp.deleteDiary(
                    Dairy(holder.text.text.toString(), holder.title.text.toString(),  holder.time.text.toString())
                )



            }
            Toast.makeText(holder.itemView.context, "deleted", Toast.LENGTH_SHORT).show()
            findNavController(holder.itemView)?.navigate(R.id.diary_list4)





        }




    }


    override fun getItemCount(): Int {
        return  dataSet.size
    }


}


