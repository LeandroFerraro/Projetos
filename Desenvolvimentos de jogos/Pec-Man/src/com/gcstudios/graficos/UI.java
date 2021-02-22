package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 18));
		g.drawString("Pontos: "+ Game.Point_atual + "/"+ Game.Point_contagem, 1100, 630);
	}
	
}
