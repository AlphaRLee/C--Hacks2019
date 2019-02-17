/*testLiquidTimer.js*/

//var psd;


function TestLiquidTimer() {
  camera.position.y = 2;
  camera.position.z = 3;
  var bd = new b2BodyDef;
  var ground = world.CreateBody(bd);

//Box to contain the simulation
  var shape = new b2ChainShape;
  shape.vertices.push(new b2Vec2(-2, 0));
  shape.vertices.push(new b2Vec2(2, 0));
  shape.vertices.push(new b2Vec2(2, 4));
  shape.vertices.push(new b2Vec2(-2, 4));
  shape.CreateLoop();
  ground.CreateFixtureFromShape(shape, 0.0);




  bd = new b2BodyDef;
  var body = world.CreateBody(bd);


  /*  Outline of Dam - Rectangle*/
bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,0.1), new b2Vec2(-0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(0.25,0.1), new b2Vec2(0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,3), new b2Vec2(0.25, 3));
body.CreateFixtureFromShape(shape, 0.1);

bd = new b2BodyDef;
body = world.CreateBody(bd);
shape = new b2EdgeShape;
shape.Set(new b2Vec2(-0.25,0.1), new b2Vec2(0.25, 0.1));
body.CreateFixtureFromShape(shape, 0.1);


var psd;
psd = new b2ParticleSystemDef();
psd.radius = 0.025;
var particleSystem = world.CreateParticleSystem(psd);

// Continually create new particals
window.setInterval(createParticals, 500, particleSystem);



}

function createParticals(particleSystem){
  //echo "called";

  var shape = new b2PolygonShape;

  //shape.SetAsBoxXYCenterAngle(sizeOfXDimension, sizeofYDimension, centerPoint, ?);
  shape.SetAsBoxXYCenterAngle(0.1, 0.1, new b2Vec2(1, 3.5), 0);


  var pd = new b2ParticleGroupDef;

  //change flags?
  pd.flags = b2_tensileParticle | b2_viscousParticle;
  pd.shape = shape;
  pd.color = new b2ParticleColor(0, 0, 255, 0);
  particleSystem.CreateParticleGroup(pd);


}
