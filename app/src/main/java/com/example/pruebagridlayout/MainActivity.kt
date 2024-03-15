package com.example.pruebagridlayout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {

    private var draggedView: ImageView? = null

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.grid_view)

        val gridView = findViewById<GridView>(R.id.GridViewDragDrop)
        gridView.adapter = CuadriculaGridView(this)

        //Sección para recuperar hormigas (lado izquierdo)
        val hormigaTortuga = findViewById<ImageView>(R.id.hormigaTortuga)
        hormigaTortuga.setImageResource(R.drawable.hormigatortuga)
        val hormigaChicatana = findViewById<ImageView>(R.id.hormigaChicatana)
        hormigaChicatana.setImageResource(R.drawable.hormigachicatana)
        val hormigaFuegoEuropea= findViewById<ImageView>(R.id.hormigaFuegoEuropea)
        hormigaFuegoEuropea.setImageResource(R.drawable.hormigafuegoeuropea)


        //Sección para recuperar chapulines (lado derecho)
        val chapulinArena = findViewById<ImageView>(R.id.chapulinArena)
        chapulinArena.setImageResource(R.drawable.chapulinareana)
        val chapulinMilpa = findViewById<ImageView>(R.id.chapulinMilpa)
        chapulinMilpa.setImageResource(R.drawable.chapulinmilpa)
        val chapulinOscuro= findViewById<ImageView>(R.id.chapulinOscuro)
        chapulinOscuro.setImageResource(R.drawable.chapulinoscuro)


        //Sección para recuperar chinches (lado de abajo)
        val chincheJumil = findViewById<ImageView>(R.id.chincheJumil)
        chincheJumil.setImageResource(R.drawable.chinchejumil)
        val chincheVerde = findViewById<ImageView>(R.id.chincheVerde)
        chincheVerde.setImageResource(R.drawable.chincheverde)


        //Sección para recuperar grilloa (lado de arriba)
        val grilloCorteza = findViewById<ImageView>(R.id.grilloCorteza)
        grilloCorteza.setImageResource(R.drawable.grillocorteza)
        val grilloMatorral = findViewById<ImageView>(R.id.grilloMatorral)
        grilloMatorral.setImageResource(R.drawable.grillomatorral)



        val imageViews = arrayOf(
            hormigaTortuga,
            hormigaChicatana,
            hormigaFuegoEuropea,
            chapulinArena,
            chapulinMilpa,
            chapulinOscuro,
            chincheJumil,
            chincheVerde,
            grilloCorteza,
            grilloMatorral
        )


        //Bloque de código para que las imágenes se puedan arrastrar
        for (imageView in imageViews) {
            imageView.setOnLongClickListener { v ->
                val dragShadowBuilder = View.DragShadowBuilder(v)
                v.startDragAndDrop(null, dragShadowBuilder, v, 0)
                draggedView = v as ImageView
                true
            }
        }

        //Bloque de código para soltar las imágenes en el grid
        //Nota: aquí es donde no tiene problemas de funcionalidad
        gridView.setOnDragListener { v, event ->
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    val position = gridView.pointToPosition(event.x.toInt(), event.y.toInt())
                    if (position != GridView.INVALID_POSITION) {
                        val adapter = gridView.adapter as CuadriculaGridView
                        adapter.addItem(draggedView!!.drawable)
                        draggedView = null
                    }
                    true
                }
                else -> false
            }
        }
    }
}
