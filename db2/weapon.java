package database.db2;

public class weapon {
      public String name;
      public int damage;
      public double persecond;
      public int accuracy;
      public String des;
      public int price;
      public String url;
      weapon(String n,int d,double p,int a,String de,int bp,String u){
    	  name=n;
    	  damage=d;
    	  persecond=p;
    	  accuracy=a;
    	  des=de;
    	  price=bp;
    	  url =u;
      }
      @Override
      public String toString() { 
          return String.format("The name of this weapon is %s\nThe description of "
          		+ "this weapon is %s\nThe damage of this weapon is %d\n"
          		+ "The shooting rate of this weapon is %f\nThe accuracy of this weapon is %d\n"
          		+ "The pirce is %d\n"+"and the url is %s\n",
          		name,des,
          		damage,persecond,accuracy,price,url ); 
      } 
}
