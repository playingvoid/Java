public enum Tier {
	RESIDENTIAL,
	SMB,
	COMMERCIAL;
	
	public static Tier parseTier(String tier){
		return Tier.valueOf(tier.toUpperCase());
	}
}
