package me.mzhli.javaexample.lang;

public class EnumClass {

	enum Weight {
		FAT(100), SLIGHTFAT(90), NORMAL(80), SLIM(70), THIN(60);

		// The constructor of Enum should be private
		Weight(int wt) {
			weight = wt;
		}

		/**
		 * @return the weight
		 */
		public int getWeight() {
			return weight;
		}

		private int weight;
	}

	public static void main(String[] args) {
		for (Weight w : Weight.values()) {
			switch (w) {
			case FAT:
				System.out.println("It's Fat");
				break;
			case SLIGHTFAT:
				System.out.println("It's Slight Fat");
				break;
			case NORMAL:
				System.out.println("It's Normal");
				break;
			case SLIM:
				System.out.println("It's Slim");
				break;
			case THIN:
				System.out.println("It's Thin");
				break;
			default:
				System.out.println("Unknown");
			}
		}
	}
}
