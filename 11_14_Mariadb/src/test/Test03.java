//package test;
//
//public class Test03 {
//	public static void main(String[] args) {
//       Command delete = new DeleteCommand();
//       Command update = new UpdateCommand();
//       Command insert = new Command() {
//			@Override
//			public void exec() {
//				System.out.println(" InsertCommand exec ..");
//			}
//		};
//       
//       insert.exec();
//       delete.exec();
//       update.exec();
//	}
//}
//
//
//abstract class Command{
//	public abstract void exec() ;
//}
//
//class DeleteCommand extends Command{
//	public void exec() {
//		System.out.println(" DeleteCommand exec ..");
//	}
//}
//
//class UpdateCommand extends Command{
//
//	@Override
//	public void exec() {
//		System.out.println(" UpdateCommand exec ..");
//		
//	}
//	
//}
//
//
//
//
//
//
