<?php

	///////////////////////////////////////////////////
	//         DataLog Interface Lang File           //
	//                 by oliverw92                  //
	///////////////////////////////////////////////////
	//         French Lang File by oliverw92         //
	///////////////////////////////////////////////////
	$lang = array(
					
					"pageTitle"  => "DataLog Navigateur",
					"title" => "DataLog",
					
					"filter" => array("title" => "Options de filtrage",
									  "password" => "Mot de passe",
									  "players" => "Joueurs",
									  "xyz" => "XYZ",
									  "range" => "Gamme",
									  "keys" => "Mots-cl�s",
									  "worlds" => "Mondes",
									  "dFrom" => "Date From",
									  "dTo" => "Date To",
									  "block" => "Block",
									  "search" => "Rechercher",
									  "reverse" => "Inverser l'ordre de"),
					
					"tips" => array("hideFilter" => "Afficher / Masquer les options de filtre",
									"hideResults" => "Afficher / Masquer les r�sultats",
									"actions" => "Actions � rechercher. Vous devez s�lectionner au moins un",
									"password" => "Mot de passe pour utiliser le navigateur. Requis seulement si elle est d�finie",
									"players" => "(Facultatif) Liste des joueurs que vous souhaitez rechercher s�par�s par des virgules",
									"xyz" => "(Facultatif) Coordonn�es vous souhaitez rechercher dans",
									"range" => "(Facultatif) Gamme autour de la coords ci-dessus pour rechercher",
									"keys" => "(Facultatif) Liste des mots-cl�s s�par�s par des virgules",
									"worlds" => "(Facultatif) Liste des mondes s�par�s par des virgules. Laissez vide pour tous les mondes",
									"dFrom" => "(Facultatif) Heure et date pour commencer la recherche de",
									"dTo" => "(Facultatif) Date et heure de chercher �",
									"block" => "(Facultatif) Block � rechercher dans 'Block Break' et 'Block Place' �v�nements",
									"reverse" => "Si elle est coch�e, afin de grumes seront dans l'ordre chronologique inverse. D�cochez la case pour afficher les journaux de conversation"),
					
					"actions" => array("Bloc de Pause",
									   "Bloc de Place",
									   "Sign Place",
									   "Chat",
									   "Commande",
									   "Arriv�e",
									   "Quitter",
									   "T�l�port",
									   "Seau de Lava",
									   "Seau d'eau",
									   "Ouvrir la poitrine",
									   "Interact Porte",
									   "PVP mort",
									   "Flint et de l'acier",
									   "Levier",
									   "Bouton",
									   "Autres",
									   "Explosion",
									   "Bloc de Combustion",
									   "Formulaire de neige",
									   "Feuille Decay"),
					
					"results" => array("title" => "R�sultats",
									   "id" => "ID",
									   "date" => "Date",
									   "player" => "Joueur",
									   "action" => "Action",
									   "world" => "Monde",
									   "xyz" => "XYZ",
									   "data" => "Data"),
					
					"messages" => array("clickTo" => "Cliquez sur Rechercher pour r�cup�rer des donn�es",
										"breakMe" => "Arr�tez d'essayer de me briser!",
									    "invalidPass" => "Mot de passe invalide!",
									    "noActions" => "Vous devez s�lectionner au moins 1 action de recherche en!",
									    "noResults" => "Aucun r�sultat correspondant � ces options",
									    "error" => "Erreur!")
									  
					
					);
	
	//Convert foreign characters to entities
	array_walk_recursive($lang, "ents");
	function ents(&$item, $key) {
		$item = htmlentities($item);
	}

?>