package boxesgame;

public class Box {

	private boolean bottom = false;
	private boolean top = false;
	private boolean left = false;
	private boolean right = false;
	private char owner;
	
	public Box() {
		this.owner = ' ';
	}
	
	public Box (boolean bottom, boolean top, boolean left, boolean right) {
		this.bottom = bottom;
		this.top = top;
		this.left = left;
		this.right = right;
		this.owner = ' ';
	}
        
        @Override
        public Box clone () {
            Box box = new Box();
            box.setBottom(this.bottom);
            box.setLeft(this.left);
            box.setOwner(this.owner);
            box.setRight(this.right);
            box.setTop(this.top);
            return box;
        }
	
	public boolean isBottom() {
		return bottom;
	}
	
	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}
	
	public boolean isTop() {
		return top;
	}
	
	public void setTop(boolean top) {
		this.top = top;
	}
	
	public boolean isLeft() {
		return left;
	}
	
	public void setLeft(boolean left) {
		this.left = left;
	}
	
	public boolean isRight() {
		return right;
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public char getOwner() {
		return owner;
	}
	
	public void setOwner(char owner) {
		this.owner = owner;
	}
	
	
}
