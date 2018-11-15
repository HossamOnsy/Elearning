package com.hz.elearning.temp

//package com.hz.elearning
//
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.hz.elearning.models.Student
//import kotlinx.android.synthetic.main.recycler_row.view.*
//
//
//class DataAdapter (private val dataList : ArrayList<String>, private val listener : Listener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
//
//    interface Listener {
//        fun onItemClick(android : String)
//    }
//
//    private val colors : Array<String> = arrayOf("#EF5350", "#EC407A", "#AB47BC", "#7E57C2", "#5C6BC0", "#42A5F5")
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//
//        holder.bind(dataList[position], listener, colors, position)
//    }
//
//    override fun getItemCount(): Int = dataList.count()
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
//
//        return ViewHolder(view)
//    }
//
//    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
//
//        fun bind(android: String, listener: Listener, colors : Array<String>, position: Int) {
//
//            itemView.tv_name.text = android.name
////            itemView.setBackgroundColor(context.get.android.graphics.Color.parseColor(colors[position % 6]))
//
//            itemView.setOnClickListener{ listener.onItemClick(android) }
//        }
//    }
//}