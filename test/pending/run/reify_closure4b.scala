import scala.reflect.runtime.universe._
import scala.reflect.runtime.{universe => ru}
import scala.reflect.runtime.{currentMirror => cm}
import scala.tools.reflect.ToolBox

object Test extends App {
  def foo(y: Int): Int => Int = {
    class Foo(y: Int) {
      val y1 = y

      val fun = reify{(x: Int) => {
        x + y1
      }}
    }

    val toolbox = cm.mkToolBox()
    val dyn = toolbox.runExpr(new Foo(y).fun.tree)
    dyn.asInstanceOf[Int => Int]
  }

  println(foo(1)(10))
  println(foo(2)(10))
}