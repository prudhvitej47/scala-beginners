package com.prudhvi.lectures.variancesDemo

object FoodEmpire extends App {
	
	trait Food {
		def name: String
	}
	
	class Meat(val foodName: String) extends Food {
		def name: String = this.foodName
	}
	
	class Vegetable(val foodName: String) extends Food {
		def name: String = this.foodName
	}
	
	class WhiteMeat(override val foodName: String) extends Meat(foodName)
	
	// covariant type
	// making A covariant implies that for two types A and B where A is a subtype of B, then List[A] is a subtype of List[B]
	trait Recipe[+A] {
		def name: String
		
		def ingredients: List[A]
	}
	
	// contravariant type
	// making A contravariant implies that for two types A and B where A is a subtype of B, then List[B] is a subtype of List[A]
	trait Chef[-A] {
		def specialization: String
		
		def cook(recipe: Recipe[A]): String
	}
	
	trait Restaurant[A] {
		
		def menu: List[Recipe[A]]
		def chef: Chef[A]
		
		def cookMenu: List[String] = menu.map(chef.cook)
	}
	
	val beef = new Meat("beef")
	
	val chicken = new WhiteMeat("chicken")
	val turkey = new WhiteMeat("turkey")
	
	val carrot = new Vegetable("carrot")
	val pumpkin = new Vegetable("pumpkin")
	
	case class GenericRecipe(ingredients: List[Food]) extends Recipe[Food] {
		override def name: String = s"Generic recipe based on ${ingredients.map(_.name)}"
	}
	
	case class MeatRecipe(ingredients: List[Meat]) extends Recipe[Meat] {
		override def name: String = s"Generic recipe based on ${ingredients.map(_.name)}"
	}
	
	case class WhiteMeatRecipe(ingredients: List[WhiteMeat]) extends Recipe[WhiteMeat] {
		override def name: String = s"Generic recipe based on ${ingredients.map(_.name)}"
	}
	
	val mixRecipe = GenericRecipe(List(chicken, carrot, beef, pumpkin))
	
	val mixMeatRecipe = MeatRecipe(List(chicken, beef))
	
	val whiteMeatRecipe = WhiteMeatRecipe(List(chicken, turkey))
	
	case class GenericChef() extends Chef[Food] {
		override def specialization: String = "All food"
		
		override def cook(recipe: Recipe[Food]): String = s"I made a ${recipe.name}"
	}
	
	case class MeatChef() extends Chef[Meat] {
		override def specialization: String = "Meat food"
		
		override def cook(recipe: Recipe[Meat]): String = s"I made a ${recipe.name}"
	}
	
	case class WhiteMeatChef() extends Chef[WhiteMeat] {
		override def specialization: String = "White Meat food"
		
		override def cook(recipe: Recipe[WhiteMeat]): String = s"I made a ${recipe.name}"
	}
	
	// Chef[WhiteMeat]: Can cook only WhiteMeat
	val giuseppe = new WhiteMeatChef
	giuseppe.cook(whiteMeatRecipe)
	
	// Chef[WhiteMeat] <- Chef[Meat]: Can cook only Meat
	val alfredo = new MeatChef
	alfredo.cook(mixMeatRecipe)
	alfredo.cook(whiteMeatRecipe)
	
	// Chef[WhiteMeat]<- Chef[Meat] <- Chef[Food]: Can cook any Food
	val mario = new GenericChef
	mario.cook(mixRecipe)
	mario.cook(mixMeatRecipe)
	mario.cook(whiteMeatRecipe)
	
	case class GenericRestaurant(menu: List[Recipe[Food]], chef: Chef[Food]) extends Restaurant[Food]
	
	case class MeatRestaurant(menu: List[Recipe[Meat]], chef: Chef[Meat]) extends Restaurant[Meat]
	
	// in case of recipe cannot define a recipe of type Recipe[Meat] or Recipe[Food]
	// in case of chef can define a chef of type Chef[Meat] or Chef[Food]
	case class WhiteMeatRestaurant(menu: List[Recipe[WhiteMeat]], chef: Chef[WhiteMeat]) extends Restaurant[WhiteMeat]
	
	
	val allFood = GenericRestaurant(List(mixRecipe), mario)
	val foodParadise = GenericRestaurant(List(mixMeatRecipe), mario)
	val superFood = GenericRestaurant(List(whiteMeatRecipe), mario)
	
	val meat4all = MeatRestaurant(List(mixMeatRecipe), alfredo)
	val meetMyMeat = MeatRestaurant(List(whiteMeatRecipe), alfredo)
	val meat4allAgain = MeatRestaurant(List(mixMeatRecipe), mario)
	val meetMyMeatAgain = MeatRestaurant(List(whiteMeatRecipe), mario)
	
	val notOnlyChicken = WhiteMeatRestaurant(List(whiteMeatRecipe), giuseppe)
	val whiteIsGood = WhiteMeatRestaurant(List(whiteMeatRecipe), alfredo)
	val wingsLovers = WhiteMeatRestaurant(List(whiteMeatRecipe), mario)
}
