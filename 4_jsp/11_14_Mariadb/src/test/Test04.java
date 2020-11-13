package test;
import javax.swing.JOptionPane;
public class Test04 {
	public static void main(String[] args) {
		
        Command insert = new Command() {
			public void exec() {
				System.out.println(" InsertCommand exec ..");
			}
		};
		Command update = new UpdateCommand();
		Command delete = () -> {
				System.out.println(" DeleteCommand exec ..");
		};
		String cmd = JOptionPane.showInputDialog("1.insert , 2.delete,3.update,4.quit");
		//1.insert , 2.delete,3.update,4.quit
		switch (cmd) {
			case "1":
				insert.exec();
				break;
			case "2":
				delete.exec();
				break;
			case "3":
				update.exec();
				break;
			case "4":
				return;
			default:
				System.out.println("메뉴선택이 잘못입력했습니다.");
				break;
		}
		
	}
}


@FunctionalInterface
interface Command{
	public abstract void exec() ;
}

class DeleteCommand implements Command{
	public void exec() {
		System.out.println(" DeleteCommand exec ..");
	}
}

class UpdateCommand implements Command{

	@Override
	public void exec() {
		System.out.println(" UpdateCommand exec ..");
		
	}
	
}






