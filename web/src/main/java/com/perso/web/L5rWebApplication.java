package com.perso.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@SuppressWarnings("ALL")
public class L5rWebApplication {

	public static void main(String[] args) {
		List<MonsieurMadame> monsieurMadames = List.of(new MonsieurMadame("Invention", true), new MonsieurMadame("Malin", false));
		StockDeLivre stockDeLivre = new StockDeLivre();

		Map<Boolean, Set<MonsieurMadame>> coffretCollector = monsieurMadames.stream()
																																				.collect(Collectors.partitioningBy(MonsieurMadame::isMadame, Collectors.toSet()));

		Collectors.teeing()
	}

	public record Coffret(List<LivreMonsieurMadame> madames, List<LivreMonsieurMadame> monsieur) {
		public Coffret() {
			this(new ArrayList<>(), new ArrayList<>());
		}

		public void add(LivreMonsieurMadame livre) {
			if(livre.isMadameLivre) {
				madames.add(livre);
			} else {
				monsieur.add(livre);
			}
		}

		public Coffret addAll(Coffret coffret) {
			this.madames.addAll(coffret.madames);
			this.monsieur.addAll(coffret.monsieur);
			return this;
		}

		public CoffretCollector toCollector() {
			return new CoffretCollector(Collections.unmodifiableList(madames), Collections.unmodifiableList(monsieur));
		}
	}

	public record CoffretCollector(List<LivreMonsieurMadame> madames, List<LivreMonsieurMadame> monsieur) {

	}

	public record MonsieurMadame(String nom, boolean isMadame) {

	}

	public record StockDeLivre() {
		public LivreMonsieurMadame trouve(MonsieurMadame monsieurMadame) {
			return new LivreMonsieurMadame(monsieurMadame.nom, monsieurMadame.isMadame);
		}
	}

	public record LivreMonsieurMadame(String nom, boolean isMadameLivre) {

	}
}
