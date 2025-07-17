package CodingTestStudy.SheepAndWolf;

/*
<풀이>
규칙1. 현재 위치에서 바로 취할 수 있는 양이 있으면 무조건 취함.
규칙2. 가장 끝단에 늑대가 위치하면 그냥 삭제해도 무관함. 어차피 안갈것.
		그러면 끝단에는 반드시 양이 존재하게 된다.

노드가 늑대인 경우, 그 노드에 가져올 수 있는 양-늑대의 최대 수를 저장.

최우선순위 : 이 노드를 통과하기만 하면 늑대대비 양의 수로 이득을 볼 수 있는 노드. 최소 요구 양 수가 적을 수록 먼저 간다.
2순위 : 이 노드를 통과하면 늑대대비 양의수는 +-0지만 어쨌든 양의 수는 늘어남. 최소 요구 양 수가 적을 수록 먼저 간다.
3순위 : 이 노드를 통과하면 늑대대비 양의수는 마이너스로 손해지만, 양의 수는 늘어나기때문에 가장 마지막으로 방문하면 된다. 최소 요구 양 수가 적을 수록 반드시 먼저 가야하는 것은 아니다.

2순위까지는 갈수있으면 무조건 가면 된다. 하지만 3순위만 남았으면 어디로 가든 늑대비양수비는 줄기때문에 순서를 잘 정해야 한다. 목적은 양의 절대 수를 가장 많이 늘리는 것.

 */


import java.util.ArrayList;

public class SolutionFail {
	public class SheepPicker{
		ArrayList<Node> nodes;
		int sheep;
		int wolves;

		public SheepPicker() {
			this.nodes = new ArrayList<>();
			this.sheep = 0;
			this.wolves = 0;
		}

		public void deleteGarbage(){
			// 늑대 중 sons가 없는 것을 삭제한다.
			// 삭제하면서 그 부모를 기억하고있는다.
			// 부모의 sons에서 방금 삭제한 자식을 set에서 삭제한다.
			// 부모가 늑대이고 sons가 없으면 이 함수를 다시 호출한다.
			// 위와 같은 과정을 한번 거치면 끝에 늑대가 오는 쓰레기 값이 모두 없어진다.
		}

		public void pickNode(Node node){
			// node를 취한다.
			// node의 자식 중 양이 있으면 그역시 자동으로 취하기 위해 이 함수를 다시 호출한다.
			// 이 과정을 거치면 node 밑으로 오는 양들은 다 취하게 된다.
		}


	}
	public class Node{
		Node parent;
		ArrayList<Node> sons;
		boolean wolf;
		boolean visited;

	}
	public int solution(int[] info, int[][] edges) {



		int answer = 0;
		return answer;
	}
}