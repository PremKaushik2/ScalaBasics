package patterns

object AllPatterns extends App {
  
  //1 constants
  val x: Any="scala"
  val constants = x match {
    case 1 => "a number"
    case "scala" => "The scala" 
    case true    => "Boolean"
      
  }
  
  // match anything
  //wildcard
  val matchanything= x match{
    case _ => 
  }
  
  // 3 a tuple 
  val atuple=(1,2)
  val matchaTuple=atuple match{
    case (1,1) => 
    case (something,2) => s"I have  found $something"  
  }
  
  val nestedTuple=(1,(2,3))
  val matchnestedTuple= nestedTuple match {
    
    case (_ , (k,v)) => "Right combination"
  }
}