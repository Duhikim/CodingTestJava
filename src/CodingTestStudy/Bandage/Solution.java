package CodingTestStudy.Bandage;


class Solution {
    public static int solution(int[] bandage, int health, int[][] attacks) {
        int tick = 0;        
        // bandage[0] : 회복 시간	 bandage[1] : 초당 회복량	bandage[2] : 보너스 체력
        int healTick = 0; // 연속 성공 시간
        int lastTick = attacks[attacks.length-1][0];
        int maxHealth = health;
        boolean beAttack = false;
        int sIdx = 0;
        
        while (tick < lastTick) {
        	tick++;
        	        	
        	// 공격 처리
        	for(int i=sIdx; i<attacks.length; i++) {
        		if(tick < attacks[i][0]) {
        			sIdx = i;
        			break;        		
        		}
        		if(tick == attacks[i][0]) {
        			beAttack = true;
        			health -= attacks[i][1];
        			healTick = 0;
        			//System.out.println(tick + " 틱, 공격 당함, 남은 체력 " + health );
        			if(health <= 0) return -1;
        			sIdx = i;
        			break;
        		}
        	}
        	if(beAttack) {        		
        		beAttack = false;
        		continue;
        	}
        	
        	// 붕대질 처리
        	health += bandage[1];
        	healTick++;
        	if(healTick == bandage[0]) {
        		health += bandage[2];
        		healTick = 0;
        	}
        	if(health > maxHealth) health = maxHealth;
        	
        	//System.out.println(tick + " 틱, 붕대질 성공, 남은 체력 " + health );
        }        
        
        return health;
    }
}