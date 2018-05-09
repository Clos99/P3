
	import java.util.ArrayList;

	public class Sample {

			//member variables
			protected ArrayList<Double> dataList;
			double min;
			double max;
			double standardDeviation;
			double mean;
			
			public Sample() {//initializes variables
				this.min = 0;
				this.max = 0;
				this.standardDeviation = 0;
				this.mean = 0;
				this.dataList = new ArrayList<Double>();
			}
			
			public void addData(Double data) {//allows you to add data from another class
				this.dataList.add(data);
			}
			
			public void computeStats() {//calculates data 
				this.min = Double.MAX_VALUE;
				this.max = Double.MIN_VALUE;
				this.mean = 0;
				this.dataList.forEach(dataItem -> {
					if(dataItem < this.min) {
						this.min = dataItem;
					}
				if(dataItem > this.max) {
					this.max = dataItem;
				}
				this.mean +=  dataItem;
				});
				this.mean = this.mean / this.dataList.size();
				
				
				this.standardDeviation = 0;
				for (int i = 0; i < this.dataList.size(); i++) {
					this.standardDeviation = Math.pow(this.dataList.get(i) - this.mean, 2) + this.standardDeviation;
				}
					this.standardDeviation = this.standardDeviation / (this.dataList.size() - 1);
					this.standardDeviation = Math.sqrt(standardDeviation);
				}
			public String toString() {
				
				return "size = " + this.dataList.size() + ", Max: " + String.format("%,.3f",this.max) + ", Min: " + String.format("%,.3f",this.min) + ", risk: " + String.format("%,.3f",this.standardDeviation) + ", Mean: " + String.format("%,.3f",this.mean);
			}
			
			
			
			//getters
			public Double getData(int index) {
				return this.dataList.get(index);
			}
			
			public ArrayList<Double> getDataList() {
				return dataList;
			}

			public double getMin() {
				return min;
			}

			public double getMax() {
				return max;
			}

			public double getStandardDeviation() {
				return standardDeviation;
			}

			public double getMean() {
				return mean;
			}


		}

