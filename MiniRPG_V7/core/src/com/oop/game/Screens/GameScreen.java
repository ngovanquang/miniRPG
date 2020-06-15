package com.oop.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;

import java.security.Key;
import java.util.ArrayList;

// box2d imports
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.ContactImpulse;

// tilemap imports
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.oop.game.Actor.MainCharacter;
import com.oop.game.Animation.Box2DActor;
import com.oop.game.Animation.Enemy;
import com.oop.game.Animation.GameUtils;
import com.oop.game.Animation.Player;

public class GameScreen extends BaseScreen
{
    private MainCharacter mainCharacter;
    private Player player;
    private Enemy enemy;
    private Enemy baseEnemy;
    private World world;
    private int coins = 0;
    
   // private ParticleActor baseSparkle;
    
    private ArrayList<Box2DActor> removeList;
    
    TiledMap tiledMap;
    OrthographicCamera tiledCamera;
    TiledMapRenderer tiledMapRenderer;
    int[] backgroundLayer = {0,1,2};
    int[] tileLayer       = {3,4,5,6};
    
    // game world dimensions
    final int tileSize = 16;
    final int mapWidth = tileSize * 100; // bigger than before!
    final int mapHeight = tileSize * 100;

    public GameScreen(BaseGame g)
    {  super(g);  }

    public void addSolid(RectangleMapObject rmo)
    {
        Rectangle r = rmo.getRectangle();            
        Box2DActor solid = new Box2DActor();
        solid.setPosition(r.x, r.y);
        solid.setSize(r.width, r.height);
        solid.setStatic();
        solid.setShapeRectangle();
        solid.initializePhysics(world);
    }
        
    public void create() 
    {

        world = new World(new Vector2(0, 0f), true);
        removeList = new ArrayList<Box2DActor>();
        
        // background image provided by tilemap

        // player
        player = new Player();

        Animation<TextureRegion> playerWalkD = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,1, 0.05f,PlayMode.LOOP_PINGPONG );

        player.storeAnimation( "walkDown", playerWalkD );

        Animation<TextureRegion> playerWalkL = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,4, 0.05f,PlayMode.LOOP_PINGPONG );

        player.storeAnimation( "walkLeft", playerWalkL );


        Animation<TextureRegion> playerWalkR = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,4, 0.05f,PlayMode.LOOP_PINGPONG );

        player.storeAnimation( "walkRight", playerWalkR );


        Animation<TextureRegion> playerWalkU = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,3, 0.05f,PlayMode.LOOP_PINGPONG );

        player.storeAnimation( "walkUp", playerWalkU );



        // stand

//        Animation<TextureRegion> playerStandL = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,2,4, 0.15f,PlayMode.LOOP_PINGPONG );
//
//        player.storeAnimation( "standLeft", playerStandL );
//
//
//        Animation<TextureRegion> playerStandR = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,4,4, 0.15f,PlayMode.LOOP_PINGPONG );
//
//        player.storeAnimation( "standRight", playerStandR );
//
//
//        Animation<TextureRegion> playerStandU = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,3,4, 0.15f,PlayMode.LOOP_PINGPONG );
//
//        player.storeAnimation( "standUp", playerStandU );
//
//        Animation<TextureRegion> playerStandD = GameUtils.parseSpriteSheet("animation.png", 4, 4,1,4, 0.15f,PlayMode.LOOP_PINGPONG );
//
//        //player.storeAnimation( "walk", playerWalkL );
//        player.storeAnimation( "standDown", playerStandD );


//        Animation<TextureRegion> jump = GameUtils.parseSpriteSheet("ada_0.png", 4, 4,1,1, 0.15f,PlayMode.LOOP_PINGPONG );
//        player.storeAnimation( "jump", jump );

        player.setSize(64,64);
        mainStage.addActor(player);
        // set other player properties later...
        
        // coin
        baseEnemy = new Enemy();

        Animation<TextureRegion> enemyWalkD = GameUtils.parseSpriteSheet("dante_0.png", 4, 4,1, 0.05f,PlayMode.LOOP_PINGPONG );

        baseEnemy.storeAnimation( "walkDownE", enemyWalkD );

        Animation<TextureRegion> enemyWalkL = GameUtils.parseSpriteSheet("dante_0.png", 4, 4,4, 0.05f,PlayMode.LOOP_PINGPONG );

        baseEnemy.storeAnimation( "walkLeftE", enemyWalkL );


        Animation<TextureRegion> enemyWalkR = GameUtils.parseSpriteSheet("dante_0.png", 4, 4,4, 0.05f,PlayMode.LOOP_PINGPONG );

        baseEnemy.storeAnimation( "walkRightE", enemyWalkR );


        Animation<TextureRegion> enemyWalkU = GameUtils.parseSpriteSheet("dante_0.png", 4, 4,3, 0.05f,PlayMode.LOOP_PINGPONG );

        baseEnemy.storeAnimation( "walkUpE", enemyWalkU );



        baseEnemy.setSize(64,64);

//        Texture coinTex = new Texture(Gdx.files.internal("coin.png"));
//        coinTex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
//        baseEnemy.storeAnimation( "default", enemyWalkD );
//
//        baseSparkle = new ParticleActor();
//        baseSparkle.load("assets/sparkler.pfx", "assets/");
        
        // load tilemap
        tiledMap = new TmxMapLoader().load("Map01.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false,viewWidth,viewHeight);
        tiledCamera.update();
        
        MapObjects objects = tiledMap.getLayers().get("ObjectData").getObjects();
        for (MapObject object : objects) 
        {
            String name = object.getName();
            // all object data assumed to be stored as rectangles
            RectangleMapObject rectangleObject = (RectangleMapObject)object;
            Rectangle r = rectangleObject.getRectangle();


            if ( name.equals("Player") )

            {

               // mainCharacter = new MainCharacter(this, 10, 10, r.getX(), r.getY());
                player.setPosition( r.getX(), r.getY() );
            }
            else if ( name.equals("Enermy") )
            {
                enemy = baseEnemy.clone();
                enemy.setPosition(r.x, r.y);
                mainStage.addActor(enemy);
                enemy.initializePhysics(world);
            }
            else if (name.equals("Door")){
               // game.setScreen( new GameScreen(game) );
            }

          //  else
                //System.err.println("Unknown tilemap object: " + name);
        }
        
        player.setDynamic();
        player.setShapeRectangle();
        player.setPhysicsProperties(0, 0f, 0f);
        player.setMaxSpeedX(2);
        player.setFixedRotation();
        player.initializePhysics(world);
        
        objects = tiledMap.getLayers().get("PhysicData").getObjects();
        for (MapObject object : objects)
        {
            if (object instanceof RectangleMapObject)
                addSolid( (RectangleMapObject)object );
            else
                System.err.println("Unknown PhysicsData object.");
        }




        world.setContactListener(
            new ContactListener()
            {
                public void beginContact(Contact contact)
                {
                    Object objC = GameUtils.getContactObject(contact, Enemy.class);
                    if (objC != null)
                    {
                        Object objP = GameUtils.getContactObject(contact, Player.class, "main");
                        if (objP != null)
                        {
                            Enemy c = (Enemy)objC;
  //                          removeList.add( c );
//                            ParticleActor sparkle = baseSparkle.clone();
//                            sparkle.setPosition(
//                                c.getX() + c.getOriginX(), c.getY() + c.getOriginY() );
//                            sparkle.start();
//                            mainStage.addActor(sparkle);
                        }
                        return; // avoid possible jumps
                    }

//                    Object objP = GameUtils.getContactObject(contact, Player.class, "bottom");
//                    if ( objP != null )
//                    {
//                        Player p = (Player)objP;
//                        p.adjustGroundCount(1);
//                        p.setActiveAnimation("stand");
//                    }
                }

                public void endContact(Contact contact)
                {
                    Object objC = GameUtils.getContactObject(contact, Enemy.class);
                    if (objC != null)
                        return;

//                    Object objP = GameUtils.getContactObject(contact, Player.class, "bottom");
//                    if ( objP != null )
//                    {
//                        Player p = (Player)objP;
//                        p.adjustGroundCount(-1);
//                    }
                }

                public void preSolve(Contact contact, Manifold oldManifold) { }

                public void postSolve(Contact contact, ContactImpulse impulse) { }
            });

    }

//    public void handleInput(float dt){
//        //control our player using immediate impulses
//        if(player.currentState != Mario.State.DEAD) {
//            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
//                player.jump();
//            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.b2body.getLinearVelocity().x <= 2)
//                player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
//                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
//            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
//                player.fire();
//        }
//
//    }
    public void direction() {
    }

    public void update(float dt) 
    {

        removeList.clear();
        world.step(1/60f, 6, 4);

        if (player.getSpeed() > 0){
            player.setVelocity(0, 0);
        }

        if (enemy.getSpeed() > 0){
            enemy.setVelocity(0, 0);
        }


//        mainCharacter.move();
        for (Box2DActor ba : removeList)
        {
            ba.destroy();
            world.destroyBody( ba.getBody() );
        }

        if( Gdx.input.isKeyPressed(Keys.LEFT) )
        {
            player.setScale(-1,1);
            //player.applyForce( new Vector2(5.0f, 0) );

            player.setVelocity(-300, 0);

            player.setActiveAnimation("walkLeft");

        }

        if( Gdx.input.isKeyPressed(Keys.RIGHT) )
        {
            player.setScale(1,1);
            player.setVelocity(300, 0);

            player.setActiveAnimation("walkRight");
        }

        if( Gdx.input.isKeyPressed(Keys.UP) )
        {
            player.setScale(1,1);
            player.setVelocity(0, 3);

            player.setActiveAnimation("walkUp");

        }

        if( Gdx.input.isKeyPressed(Keys.DOWN) )
        {
            player.setScale(1,1);
            player.setVelocity(0, -3);

            player.setActiveAnimation("walkDown");
        }

        if ((enemy.getY() - player.getY()) < 64) {
            enemy.setActiveAnimation( "walkUpE");
            enemy.setVelocity(0, 2);
        }
        else if ((enemy.getY() - player.getY() >= 64)) {
            enemy.setActiveAnimation( "walkDownE");
            enemy.setVelocity(0, -2);

        }

        if (enemy.getOriginX() < player.getOriginX()) {
            enemy.setActiveAnimation( "walkRightE");
            enemy.setVelocity(200, 0);
        }
        else if ((enemy.clone().getX() - player.getX()) >= 64) {
            enemy.setActiveAnimation( "walkLeftE");
            enemy.setVelocity(-200, 0);
        }



    }
    
    // this is the gameloop. update, then render.
    public void render(float dt) 
    {
        uiStage.act(dt);

        // only pause gameplay events, not UI events
        if ( !isPaused() )
        {
            mainStage.act(dt);
            update(dt);
        }

        // render
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Camera mainCamera = mainStage.getCamera();
        mainCamera.position.x =  player.getX() + player.getOriginX();
        mainCamera.position.y = player.getY() + player.getOriginY();
        // bound main camera to layout
        mainCamera.position.x = MathUtils.clamp(mainCamera.position.x, viewWidth/2,  mapWidth - viewWidth/2);
        mainCamera.position.y = MathUtils.clamp(mainCamera.position.y, viewHeight/2,mapHeight - viewHeight/2);
        mainCamera.update();
        
        // scroll background more slowly to create parallax effect
//        tiledCamera.position.x = mainCamera.position.x/4 + mapWidth/4;
//        tiledCamera.position.y = mainCamera.position.y;
//        tiledCamera.update();
//        tiledMapRenderer.setView(tiledCamera);
        tiledMapRenderer.render(backgroundLayer);



        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);
        mainStage.draw();
        uiStage.draw();
        tiledMapRenderer.render(tileLayer);





    }
    
    public boolean keyDown(int keycode)
    {
        if (keycode == Keys.P)    
            togglePaused();

        if (keycode == Keys.R)    
            game.setScreen( new GameScreen(game) );
        if (keycode == Keys.M)
            game.setScreen(new MainMenuScreen(game));

     if (keycode == Keys.SPACE /* player.isOnGround()*/ )
        {
            Vector2 jumpVec = new Vector2(0,3);
            player.applyImpulse(jumpVec);
            player.setActiveAnimation("jump");
        }

        return false;
    }
}