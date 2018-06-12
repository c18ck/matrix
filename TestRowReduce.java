package matrixCKK;

public class TestRowReduce {

	public static void main(String[] args) 
	{
//		Matrix alice = new Matrix (3,6);
//		alice.setEntry(0, 0, -5);
//		alice.setEntry(0, 1, 4);
//		alice.setEntry(0, 2, -3);
//		alice.setEntry(0, 3, 1);
//		alice.setEntry(0, 4, 0);
//		alice.setEntry(0, 5, 0);
//
//		alice.setEntry(1, 0, 10);
//		alice.setEntry(1, 1, -7);
//		alice.setEntry(1, 2, 6);
//		alice.setEntry(1, 3, 0);
//		alice.setEntry(1, 4, 1);
//		alice.setEntry(1, 5, 0);
//		
//		alice.setEntry(2, 0, 8);
//		alice.setEntry(2, 1, -6);
//		alice.setEntry(2, 2, 5);
//		alice.setEntry(2, 3, 0);
//		alice.setEntry(2, 4, 0);
//		alice.setEntry(2, 5, 1);

//		Matrix alice = new Matrix (2,3);
//		alice.setEntry(0, 0, 1);
//		alice.setEntry(0, 1, 3);
//		alice.setEntry(0, 2, -1);
//		alice.setEntry(1, 0, 0);
//		alice.setEntry(1, 1, 1);
//		alice.setEntry(1, 2, 7);



//		Matrix alice = new Matrix (3,5); 
//
//		for(int i=0; i<3; i++) //Filling alice
//		{
//			for(int j=0; j<5; j++)
//			{
//				if(i+j==2)
//					alice.setEntry(i, j, i+1);
//				else if(i+j==4)
//					alice.setEntry(i, j, j+1);
//				else
//					alice.setEntry(i, j, 0);
//			}
//		}
//
//		alice.setEntry(2,4, 7);

//		alice.print(); //printing alice
//
//		System.out.println("\n");
//
//		alice.rowreduce().print(); //printing the Matrix which results when alice is row reduced
//
//		System.out.println("\n");
//
//		alice.print(); //printing alice again!
		
		
		Matrix trixie = new Matrix(4,3);
        
        trixie.setEntry(0, 0, 2.5);
        trixie.setEntry(0, 1, 0);
        trixie.setEntry(0, 2, 4);
        trixie.setEntry(1, 0, -1);
        trixie.setEntry(1, 1, -1);
        trixie.setEntry(1, 2, 1);
        trixie.setEntry(2, 0, 0);
        trixie.setEntry(2, 1, 0);
        trixie.setEntry(2, 2, -.5);
        trixie.setEntry(3, 0, 3);
        trixie.setEntry(3, 1, 9);
        trixie.setEntry(3, 2, 2);

        trixie.print();
       
        System.out.println("\n");
        
        trixie.rowreduce().print();
        
        System.out.println("\n");
        
        trixie.print();
		
	}

}

