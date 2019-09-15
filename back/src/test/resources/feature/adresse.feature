Feature: Modifier l'adresse d'un abonné
	
		Scenario: Modification de l'adresse d'un abonné résidant en France sans date d'effet
			Given un abonné avec une adresse principale active en France
		        When le conseiller connecté à FACE modifie l'adresse de l'abonné sans date d'effet
			Then l'adresse de labonné modifiée est enregistrée sur lensemble des contrats de l'abonné
			
				

       		