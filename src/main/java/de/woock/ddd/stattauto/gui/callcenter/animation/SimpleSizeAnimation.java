package de.woock.ddd.stattauto.gui.callcenter.animation;

import com.jgoodies.animation.AbstractAnimation;

import de.woock.ddd.stattauto.gui.callcenter.view.TabView;

public class SimpleSizeAnimation extends AbstractAnimation {

	TabView mainPanel;
	double size = (450.0 - 159) / 250;
	
	public SimpleSizeAnimation(long duration, TabView mainPanel) {
		super(duration, true);
		this.mainPanel = mainPanel;
		addAnimationListener(new SizeAnimationListener());
	}

	@Override
	protected void applyEffect(long time) {
		mainPanel.setSize(480, (int)(159+time * size));
	}
}
