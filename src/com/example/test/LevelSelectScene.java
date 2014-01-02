package com.example.test;

import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;

import com.example.test.SceneManager.SceneType;

public class LevelSelectScene extends BaseScene implements
		IOnMenuItemClickListener
{
	private MenuScene levelSelectChildScene;
	private final int LEVEL_1 = 0;
	private final int LEVEL_2 = 1;
	private final int LEVEL_3 = 2;

	private void createBackground()
	{
		SpriteBackground pBackground = new SpriteBackground(new Sprite(400,
				240, resourcesManager.level_background_region, vbom));
		this.setBackground(pBackground);
	}

	private void createLevelSelectMenuChildScene()
	{
		levelSelectChildScene = new MenuScene(camera);
		levelSelectChildScene.setPosition(0, 0);

		final IMenuItem level1MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(LEVEL_1, resourcesManager.level1_region,
						vbom), 1.2f, 1);
		final IMenuItem level2MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(LEVEL_2, resourcesManager.level2_region,
						vbom), 1.2f, 1);
		final IMenuItem level3MenuItem = new ScaleMenuItemDecorator(
				new SpriteMenuItem(LEVEL_3, resourcesManager.level3_region,
						vbom), 1.2f, 1);

		levelSelectChildScene.addMenuItem(level1MenuItem);
		levelSelectChildScene.addMenuItem(level2MenuItem);
		levelSelectChildScene.addMenuItem(level3MenuItem);

		levelSelectChildScene.buildAnimations();
		levelSelectChildScene.setBackgroundEnabled(false);

		level1MenuItem.setPosition(130, 380);
		level2MenuItem.setPosition(260, 380);
		level3MenuItem.setPosition(390, 380);

		levelSelectChildScene.setOnMenuItemClickListener(this);

		setChildScene(levelSelectChildScene);
	}

	@Override
	public void createScene()
	{
		createBackground();
		createLevelSelectMenuChildScene();
	}

	@Override
	public void onBackKeyPressed()
	{
		SceneManager.getInstance().loadMenuScene(engine);
	}

	@Override
	public SceneType getSceneType()
	{
		return SceneType.SCENE_LEVEL_SELECT;
	}

	@Override
	public void disposeScene()
	{

	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY)
	{
		GameScene.setStartLevel(pMenuItem.getID() + 1);
		switch (pMenuItem.getID())
		{
		case LEVEL_1:
		case LEVEL_2:
		case LEVEL_3:
			SceneManager.getInstance().loadGameScene(engine);
			return true;
		default:
			return false;
		}
	}
}
