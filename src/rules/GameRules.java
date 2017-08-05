package rules;

import java.util.ArrayList;

public class GameRules {

	ArrayList<Boolean> status = new ArrayList<>();
	
	public GameRules(ArrayList<Boolean> deadOrAlive) {
		
		status = deadOrAlive;
	}
	
	public boolean[] ruleResultsReturn() {
		
		boolean[] result = new boolean[status.size()];
		
		for (int x = 0; x < status.size(); x++) {

			if ( x == 0 ) {
				
				result[x] = leftTopCornerUnderOrOverPopulationCheck( x );
				
			} else if ( x == 40 ) {
				
				result[x] = leftBottomCornerUnderOrOverPopulationCheck( x );
				
			} else if ( x == 7 ) {
				
				result[x] = rightTopCornerUnderOrOverPopulationCheck( x );
				
			} else if ( x == 47 ) {
				
				result[x] = rightBottomCornerUnderOrOverPopulationCheck( x );
				
			} else if ( x == 15 || x == 23 || x == 31 || x == 39 ) {
				
				result[x] = rightSideUnderOrOverPopulationCheck( x );
				
			} else if ( x == 8 || x == 16 || x == 24 || x == 32 ) {
				
				result[x] = leftSideUnderOrOverPopulationCheck( x );
				
			} else if ( x == 1 || x == 2 || x == 3 || x == 4 || x == 5 || x == 6 ) {
				
				result[x] = topSideUnderOrOverPopulationCheck( x );
				
			} else if ( x == 41 || x == 42 || x == 43 || x == 44 || x == 45 || x == 46 ) {
				
				result[x] = bottomSideUnderOrOverPopulationCheck( x );
				
			} else {
				
				result[x] = allOthersUnderOrOverPopulationCheck( x );
			}	
		}
		
		return result;
	}
	
	public boolean leftTopCornerUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+1)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean leftBottomCornerUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+-8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+1)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean rightTopCornerUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-1)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean rightBottomCornerUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+-9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-1)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean leftSideUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+-8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+1)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+8)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean rightSideUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-1)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-8)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}

	public boolean topSideUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+-1)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+1)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean bottomSideUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+-1)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+1)) == true ) {
			neighborLife++;
		}

		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
	
	public boolean allOthersUnderOrOverPopulationCheck(int x) {
		
		int neighborLife= 0;
		boolean result = status.get(x);
		
		if ( status.get((x+-9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+1)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+9)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+8)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+7)) == true ) {
			neighborLife++;
		}
		if ( status.get((x+-1)) == true ) {
			neighborLife++;
		}
		
		if ( status.get(x) == true && (neighborLife< 2 || neighborLife> 3) ) {
			result = false;
		} else if ( status.get(x) == false && neighborLife== 3 ) {
			result = true;
		}

		return result;
	}
}
