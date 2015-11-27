package courseexamples;

//import java.util.*;
import java.text.*;
import java.util.Vector;

public class fuzzyOperator
{  static DecimalFormat dfm = new DecimalFormat("0.000");

   public static void printResult(String txt, double alpha,
                                  double[] a, double result)
   {  System.out.print("Operator " + txt +  "(alpha = "+ alpha+") a = (");
      for(int i=0; (i<a.length) ; i++) System.out.print(" "+dfm.format(a[i]));
      System.out.println(") result = "+dfm.format(result));
   }

   public static double dombiTnorm(double alpha, double[] a)
   {  boolean atLeastOneZero = false;
      for(int i=0; (i<a.length) && !atLeastOneZero; i++)
         atLeastOneZero = a[i]<0.000001;
      if(atLeastOneZero) return 0.0;

      if(alpha < 0.01) alpha = 0.01; else // value very close to zero
      if(alpha > 100.0) alpha = 100.0;

      double sum = 0.0;
      for(int i=0; i<a.length; i++)  sum += Math.pow((1.0/a[i])-1.0, alpha);

      return 1.0/(1.0 + Math.pow(sum, 1.0/alpha));
   }

   public static double dombiTconorm(double alpha, double[] a)
   {  double[] aNeg = new double[a.length];
      for(int i=0; (i<a.length) ; i++)  aNeg[i] = 1 - a[i];

      return 1.0 - dombiTnorm(alpha, aNeg);
   }

   public static double min(double[] a)
   {  double aMin = 1.0;
      for(int i=0; (i<a.length) ; i++) if(a[i] < aMin) aMin = a[i];
      return aMin;
   }

   public static double max(double[] a)
   {  double aMax = 0.0;
      for(int i=0; (i<a.length) ; i++) if(a[i] > aMax) aMax = a[i];
      return aMax;
   }

   public static double aMean (double[] a)
   {  double aSum = 0.0;
      for(int i=0; (i<a.length) ; i++) aSum += a[i];
      return aSum/a.length;
   }
   
   public static double yagerTconorm(double alpha, double[] a){  
		if(!(a.length>0)) return 0.0;
		if(alpha < 0.01) alpha = 0.01; else // value very close to zero
			if(alpha > 100.0) alpha = 100.0;
		double res=a[0];
		if(a.length==1) return Math.pow(  Math.pow(res,alpha)   ,1.0/alpha);

		for(int i=1;i<a.length;i++){
			
			res=Math.min(1, 
					Math.pow(  Math.pow(res,alpha)+Math.pow(a[i],alpha)   ,1.0/alpha)	
			);
		}
		return res;
	}
   
   public double getMaximum(double[] a){
		double aMax = 0.0;
	    for(int i=0; (i<a.length) ; i++) if(a[i] > aMax) aMax = a[i];
	    return aMax;
	}
	
	public double getMinimum(double[] a){
		double aMin = 1.0;
		for(int i=0; (i<a.length) ; i++) if(a[i] < aMin) aMin = a[i];
		return aMin;
	}
	
	public static double getAritmeticMean(double[] a){  
		double aSum = 0.0;
		for(int i=0; (i<a.length) ; i++) aSum += a[i];
		return aSum/a.length;
	}

	public static double getHarmonicMean(double[] a){
		if(a.length<=0) return 0;
		double sum=0;
		for(int y=0;y<a.length;y++){
			sum=sum+(1/a[y]);
		}
		return a.length/sum;
	}

	public static double getGeometricMean(double[] a){
		if(a.length<=0) return 0;
		double prod=1.0;
		for(int y=0;y<a.length;y++){
			prod=prod*(a[y]);
		}
		return Math.pow(prod, (1.0/a.length));
	}

	public static double getAlgebraicSum(double vals[]){
		
		
			double prod=1;
			for(int u=0;u<vals.length;u++){
				prod=prod*(1-vals[u]);
			}
			//Gui.guiprint("product: "+prod);
			return 1-prod;
	
		
	}

  public static void main(String[] args)
  {  double[] a = {0.5, 0.7, 0.4, 0.3, 0.7};
     //double[] a = {1.0, 1.0, 1.0, 1.0, 1.0};
	 // double[] a = {0.3529, 0.5294 , 0.645};
	 // double[] a = {0.3, 0.6 , 0.9};
     double[] alpha = {0, 0.2, 0.4, 0.6, 0.8, 1, 1.5, 2, 3, 10, 10000};

     System.out.println();
     //for(int j=0; (j<alpha.length) ; j++)
        //printResult("Dombi t-norm", alpha[j] , a, dombiTnorm(alpha[j], a));

     System.out.println();
     for(int j=0; (j<alpha.length) ; j++)
        printResult("Dombi t-conorm", alpha[j] , a, dombiTconorm(alpha[j], a));

     System.out.println("\nmin(a) = "+dfm.format(min(a))
                       +", max(a) = "+dfm.format(max(a))
                       +", aMean(a) = "+dfm.format(aMean(a)));
     
     System.out.println();
     for(int j=0; (j<alpha.length) ; j++)
         printResult("Yager t-conorm", alpha[j] , a, yagerTconorm(alpha[j], a));
     
     System.out.println();
     
     System.out.println("\nmin(a) = "+dfm.format(min(a))
             +", max(a) = "+dfm.format(max(a))
             +", alg sum(a) = "+dfm.format(getAlgebraicSum(a))
             +", aMean(a) = "+dfm.format(aMean(a))
             +", geometric Mean(a) = "+dfm.format(getGeometricMean(a))  
             +", harmonic Mean(a) = "+dfm.format(getHarmonicMean(a))
            
     );
  }
}

