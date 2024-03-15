package com.example.pruebagridlayout

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.TextView

class CuadriculaGridView(private val context: Context) : BaseAdapter() {

    private val items = mutableListOf<Any>()

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val textView: TextView

        if (convertView == null) {
            textView = TextView(context)
            textView.layoutParams = AbsListView.LayoutParams(
                parent!!.width / 2,
                parent.height / 2
            )
            textView.gravity = android.view.Gravity.CENTER
            textView.textSize = 18f
        } else {
            textView = convertView as TextView
        }

        // Bloque pa' wachar que sí se crean
        //Nota: al correr la aplicación ya no se muestran los colores de los cuadrantes
        //Supongo que aquí está el problema de funcionalidad del "drop"
        when (position) {
            0 -> textView.setBackgroundColor(Color.BLUE)
            1 -> textView.setBackgroundColor(Color.GREEN)
            2 -> textView.setBackgroundColor(Color.MAGENTA)
            3 -> textView.setBackgroundColor(Color.YELLOW)
        }
        textView.text = "Cuadrante ${position + 1}"

        return textView
    }

    fun addItem(item: Any) {
        items.add(item)
        notifyDataSetChanged()
    }
}



