package database.db1;

public class weapon {
      public String name;
      public int damage;
      public double persecond;
      public int accuracy;
      public String des;
      public int price;
      weapon(String n,int d,double p,int a,String de,int bp){
    	  name=n;
    	  damage=d;
    	  persecond=p;
    	  accuracy=a;
    	  des=de;
    	  price=bp;
      }
      @Override
      public String toString() { 
          return String.format("The name of this weapon is %s\nThe description of "
          		+ "this weapon is %s\nThe damage of this weapon is %d\n"
          		+ "The shooting rate of this weapon is %f\nThe accuracy of this weapon is %d",name,des,
          		damage,persecond,accuracy ); 
      } 
}
