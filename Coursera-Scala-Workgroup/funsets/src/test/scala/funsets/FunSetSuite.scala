package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }
 test("singletonSet(2) contains 2") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s2, 2), "Singleton")
    }
  }
  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
    test("intersect contains only common elements") {
    new TestSets {
      val s = union(s1, s2)
      val t=  union(s2,s3)
      val inter = intersect(s,t)
      assert(!contains(inter, 1), "Interset 1")
      assert(contains(inter, 2), "Interset 2")
      assert(!contains(inter, 3), "Interset 3")
      
      
    }
  }
     test("diff contains only  elements in first set not in second set") {
    new TestSets {
      var s = union(s1,s2)
      printSet(s)
      var t=  union(s2,s3)
      printSet(t)
      var difference = diff(s,t)
      printSet(difference)
      assert(contains(difference, 1), "Difference 1")
      assert(!contains(difference, 2), "Difference 2")
      assert(!contains(difference, 3), "Difference 3")
      difference = diff(t,s)
      printSet(difference)
      assert(!contains(difference, 1), "Difference 1")
      assert(!contains(difference, 2), "Difference 2")
      assert(contains(difference, 3), "Difference 3")
      s=union(s,s4)
      t=union(t,s4)
      printSet(s)
      printSet(t)
      difference = diff(s,t)
      printSet(difference)
      assert(contains(difference, 1), "Difference 1")
      assert(!contains(difference, 2), "Difference 2")
      assert(!contains(difference, 3), "Difference 3")
      assert(!contains(difference, 4), "Difference 3")
    }
  }
     test("exists test to see if a value exists which satisfies condition function") {
    new TestSets {
      var s = union(s1,s2)
      printSet(s)
      
     
      assert(exists(s,x => x<2), "exists < less than 2")
      assert(!exists(s,x => x> 2), "does not  exist > 2")
      s= union(s,s4)
      assert(exists(s,x => x> 2), "does   exist > 2")
    

      assert(!exists(s,x => x==3), "does not  exist = 3")

    }
  }
     
     test("for all test to see if all value in set satisfies  the satisfies condition function") {
      new TestSets {
      var s = union(s1,s2)
      printSet(s)
      
     
      assert(!forall(s,x => x<2), "forall <  2")
      assert(forall(s,x => x> 0), "for all > 0")
      s= union(s,s4)
      assert(!forall(s,x => x> 2), "for all > 2")
    

      assert(forall(s,x => x< 10), "for all  <10")
      assert(forall(s,x => x <= 4), "for all  <10")
      assert(!forall(s,x => x==3), "for all == 3")
    }
  }
     
     test("filter test to return  only items of set  which satrify filter ") {
    new TestSets {
      var s = union(s1,s2)
         s= union(s,s4) 
      
      var f=filter(s, x => x <2)
      println("filter sets")
      printSet(s)
      println("filter x<2  to ")
      printSet(f)
      assert(contains(f,1), "filter test 1 ")
      assert(!contains(f,2), "filter test 1 ")
      assert(!contains(f,4), "filter test 4")
    
       
   
      var m = filter(s,x => x > 1)
      println("filter sets")
      printSet(s)
      println("filter x>1 to ")
      printSet(m)
      assert(!contains(m,1), "filter 1")
      assert(contains(m,2), "filter 2")
    

      assert(contains(m,4), "filter 4")
    
    }
  }
     
      test("map test to  transform vlaue in set  by applying  function") {
      new TestSets {
      var s = union(s1,s2)
          s= union(s,s4) 
	      printSet(s)
	      var m=map(s,x => x*x)
	      println("mapped sets")
	      printSet(s)
	      println("maps to")
	      printSet(m)
	      assert(contains(m,1), "map 1 to 1 sqrd i.e.1")
	      assert(contains(m,4), "map 2 to 2 sqrd i.e. 4")
    
       
	     assert(contains(m,16), "map 4 to 4 sqrd i.e.16")
	     m = map(s,x => x/x)
	     assert(contains(m,1), "")
	     m = map(s, x=> x*x*x)
	     assert(contains(m,1), "map 1 to 1 sqrd i.e.1")
	     assert(contains(m,8), "map 4 to 4 sqrd i.e.16")
	     assert(contains(m,64), "map 4 to 4 sqrd i.e.16")
	    
    }
  }
}
