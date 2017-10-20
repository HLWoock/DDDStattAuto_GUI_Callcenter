package de.woock.ddd.stattauto.gui.callcenter.animation;

import org.springframework.stereotype.Service;

import com.jgoodies.animation.AnimationEvent;
import com.jgoodies.animation.AnimationListener;

@Service
public class SizeAnimationListener implements AnimationListener{

	@Override
	public void animationStarted(AnimationEvent evt) {
		System.out.println("Animation started");
	}

	@Override
	public void animationStopped(AnimationEvent evt) {
		System.out.println("Animation stopped");
	}

}
