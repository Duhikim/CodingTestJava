package CodingTestStudy.RemoveString;

public class Solution_ver1
{
	static StringList start = new StringList('0');

	public static class StringList{
		public char value;
		public StringList pre;
		public StringList post;

		public StringList(char ch){
			this.value = ch;
			pre = null;
			post = null;
		}
		StringList delete(){
			this.pre.post = this.post;
			if(this.post != null) this.post.pre = this.pre;
			return (this.pre != start)? this.pre: this.post;
		}
	}
	public int solution(String s)
	{
		int len = s.length();

		StringList curr, pre = start;

		for(int i=0; i<len; i++){
			curr = new StringList(s.charAt(i));
			pre.post = curr;
			curr.pre = pre;
			pre = curr;
		}
		curr = start.post;
		while(curr != null){
			if(curr.post!= null && curr.value == curr.post.value){
				curr.post.delete();
				curr = curr.delete();
			}
			else{
				curr = curr.post;
			}
		}

		return (start.post==null)?1: 0;
	}

	public static void main(String[] args) {
		Solution_ver1 sol = new Solution_ver1();
		sol.solution("cdcd");
	}
}