package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    var mycount = countChange(4,List(2,1))
    println("result from 4 : "+ mycount)
    var mycount2 = countChange(300,List(5,10,20,50,100,200,500))
    println("result from 300 : "+mycount2)
    println("Max value of list (5,10,20,50,100,200,500)" + max(List(5,10,20,50,100,200,500)))
  }

  
/**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =  {
    
   def factorial(n:Int) : Int = {
 
									
									def factorialAcc(acc: Int, n: Int): Int= {
									if (n <= 1) acc
									else factorialAcc(n * acc, n - 1)
									}
									 
									factorialAcc(1, n)
								}
    
    def partialFactorial(s : Int, e: Int) : Int = {
 
													
													def partialFactorialAcc(s : Int, e: Int, acc: Int): Int = {
																												if(e >= s) acc
																												else  partialFactorialAcc(s - 1, e, s * acc)
																												}
 
													partialFactorialAcc(s, e, e)
    												}

if((c <=0) || (r <= 0) || (r <= c)) {
1
}
else {
partialFactorial(r, c+1)/factorial(r-c)
}


}
    
    
 
    
    /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    var perCount = 0
     if (chars.isEmpty) throw new NoSuchElementException
    def  countOpen(mychars: List[Char],  count: Int):Int ={
    
   var currentcount = count
   val opened:Char = '('
   
    val closed:Char =')'    
    if (currentcount >= 0){
						    if(!mychars.isEmpty)
						    {
						     if(mychars.head == opened ) {
						    							currentcount =  count+1
						    							}
						     else if (mychars.head == closed){
						    								currentcount= count-1
						    								} 
						    }
						   }
    
   mychars match{
     			case x :: tail => countOpen(tail, currentcount)
     			case Nil => currentcount
   				}
    }
   
   
   
     

   
  
    
    perCount = countOpen(chars,perCount)
    if (perCount ==0) true else false
        
  }
    
    
    
    
    
  
 def max(xs: List[Int]): Int ={
    if (xs.isEmpty) throw new NoSuchElementException
  xs.reduceLeft((x, y) => if (x > y) x else y)
  }
  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int ={
		


  var count = 0
        /** if the number is 0 or the list is empty then return a value of 0 */
      if (money  <= 0 || coins.isEmpty)     
      {
        count
      }
      else
      {  
       /** work out the remainder if the money values is divided by the first coin*/
         var moneyleft = money%coins.head
         
         /** subtract the largest coin for the money  check that the value is still positive */
         var amountminuslargestcoin = money - max(coins)
         println("amount left after max removed"+ amountminuslargestcoin)
        var matches = amountminuslargestcoin/ coins.head
        if (matches==0){	count += 1  
          
        					}
        if(moneyleft==0  ){	count += 1 }
         if(amountminuslargestcoin ==0){count += 1 }
        count+=countChange(amountminuslargestcoin,coins.tail)	
        count+=countChange(moneyleft, coins.tail)
        	 												
        	 											
          
        
  
     /** if (moneyleft==0 )  { println("in moneyleft ==0")
        	 					count +=  1
        	 					coins match{
										       case x :: tail => count += countChange(money, tail)
										       case Nil => count
        	 								}
										          
        	 												      	 											
							}
         else if (moneyleft >0){ 
							coins match{
										       	case x :: tail => count += countChange(moneyleft, tail)
										        case Nil => count
										}
										       
										           
					 	} */
       
        
           
       
          count
        
      }
     
   
 
}
}
