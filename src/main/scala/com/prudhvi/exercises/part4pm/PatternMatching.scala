package com.prudhvi.exercises.part4pm

object PatternMatching extends App {
    trait Expr

    case class Number(n: Int) extends Expr
    case class Sum(e1: Expr, e2: Expr) extends Expr
    case class Product(e1: Expr, e2: Expr) extends Expr

    def show(expr: Expr): String = expr match {
        case Number(n) => s"$n"
        case Sum(e1: Expr, e2: Expr) => show(e1) + " + " + show(e2)
        case Product(e1: Expr, e2: Expr) => {
            def maybeShowParanthesis(expr: Expr): String = expr match {
                case Product(_, _) => show(expr)
                case Number(_) => show(expr)
                case Sum(_, _) => "(" + show(expr) + ")"
            }

            maybeShowParanthesis(e1) + " * " + maybeShowParanthesis(e2)
        }
    }

    println(show(Product(Sum(Number(1), Number(10)), Sum(Number(1), Number(2)))))
    println(show(Product(Number(3), Sum(Number(1), Number(2)))))
    println(show(Sum(Product(Number(1), Number(4)), Number(1))))
    println(show(Sum(Sum(Number(2), Number(3)), Number(4))))
}
