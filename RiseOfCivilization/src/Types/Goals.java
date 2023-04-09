package Types;

public enum Goals {
	//primary goals
	ExpandedSlots("City Expansion",1),
	ProductionBuilt("Production Buildings",1),
	TrainingBuilt("Training Buildings",1),
	KilledEnnemies("Wolf Slaying",1),
	CollectResources("Resource Collector",1),
	//secondary goals
	SoldResources("Merchandising",2),
	BoughtResources("Trading",2),
	TrainedWorkers("Citizen Training",2);
	
	private final String name;
	public final int importance;
	
	private Goals(String str, int n) {
		name = str;
		importance = n;
	}
}
