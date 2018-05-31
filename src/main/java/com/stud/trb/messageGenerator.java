package com.stud.trb;

import java.util.Random;

public final class messageGenerator {
	private static final String[] msgs = {
			"Помните, мы с вами говорили?",
			"Вам вообще интересно?",
			"Вы вообще слушаете?",
			"Интересно, да? Интересная ситуация!",
			"Понятно, да? Но что с этим делать?",
			"По этому, если мы имеем сеть...",
			"Ну понято, да?!",
			"Я в свое время экспериментировал!",
			"Ты кусок!"
	};
	private static final String[] names = {
			"Артем",
			"Саня",
			"Русик",
			"Коля"
	};
	
	public static final String generateMessage() {
		int number = rand(0, msgs.length - 1);
		
		return msgs[number];
	}
	
	public static final String generateName() {
		int number = rand(0, names.length - 1);
		
		return names[number];
	}
	
	private static final int rand(int min, int max) {
		Random random = new Random();
		int number = random.nextInt(max - min + 1) + min;
		
		return number;
	}
}
