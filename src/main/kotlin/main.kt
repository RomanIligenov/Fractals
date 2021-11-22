
import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.geomTile
import jetbrains.letsPlot.letsPlot
import org.kotlinmath.*


fun main() {
 val xs_3 = ArrayList<Double>()
 val ys_3 = ArrayList<Double>()
 val zs_3 = ArrayList<Double>()
   /* var leftx:Double = -2.0
    var lefty:Double = -2.0
    val dx:Double = 0.02
    val dy:Double = 0.02
 while (leftx <= 2.0)  {
        leftx += dx
        lefty = -2.0
     while (lefty <= 2.0) {
         lefty += dy
         xs_3.add(leftx)
         ys_3.add(lefty)
     }
    }*/
    for (i in 0..280){
        for (j in 0..160){
            xs_3.add((i.toDouble()-140)/70)
            ys_3.add((j.toDouble()-80)/40)
        }
    }

   fun <A,B,C> compose(f:(B) -> C, g:(A) -> B): (A) -> C = { x -> f(g(x)) }

    fun apply (f:(Complex)->Complex, n:Int):(Complex)->Complex = if (n==1) f else compose (apply(f,n-1),f)
    val z0 = complex (-0.4,0.6)
 for (i in 1..45241){
    val z1 = complex(xs_3[i-1], ys_3[i-1])
    fun g(x:Complex) = x*x + z0
    var n:Int = 0
     var k:Int = 0
    while (n<150){
    n += 1
    var h = apply(::g, n)
    if ( (h(z1)).mod > 2 ) {
        k += 1
        zs_3.add(n.toDouble())
        break
    }
    }
     if (k == 0) zs_3.add(n.toDouble())

 }



    val data_3 = mapOf<String, Any>("x" to xs_3, "y" to ys_3, "iteration" to zs_3)
    val fig_3 = letsPlot(data_3) +
            geomTile() {x = "x"; y = "y"; fill = "iteration"; color = "iteration"}

    ggsave(fig_3, "plot.png")

}
