/*testLiquidTimer.js*/

//var psd;

//var particles = [];
//var stage = new Stage(document.getElementById('canvas'));
var gravity = new b2Vec2(0, 10);
var world = new b2World(gravity);
//var liquidSimulation = new LiquidSimulation(world);


function TestLiquidTimer() {
  camera.position.y = 2;
  camera.position.z = 3;
  var bd = new b2BodyDef;
  var ground = world.CreateBody(bd);



//Box to contain the simulation
/*
  var shape = new b2ChainShape;
  shape.vertices.push(new b2Vec2(-2, 4));
  shape.vertices.push(new b2Vec2(-2, 0));
  shape.vertices.push(new b2Vec2(2, 0));
  shape.vertices.push(new b2Vec2(2, 4));
  // shape.CreateLoop();
 ground.CreateFixtureFromShape(shape, 0.0);
*/

var shape1 = new b2ChainShape;
shape1.vertices.push(new b2Vec2(-2, 4));
ground.CreateFixtureFromShape(shape1, 0.0);

shape = new b2ChainShape;
shape.vertices.push(new b2Vec2(-1.75, 0));
shape.vertices.push(new b2Vec2(2, 0));
shape.vertices.push(new b2Vec2(2, 4));
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

//
window.setInterval(resetParticles, 50);
// Continually create new particals
window.setInterval(createParticals, 500, particleSystem);



}

function createParticals(particleSystem){
  //echo "called";

  var shape = new b2PolygonShape;

  //shape.SetAsBoxXYCenterAngle(sizeOfXDimension, sizeofYDimension, centerPoint, ?);
  shape.SetAsBoxXYCenterAngle(0.1, 0.1, new b2Vec2(1, 3.5), 0);


  var pd = new b2ParticleGroupDef;
//  var pd = new b2ParticleDef;

  //change flags?
  pd.flags = b2_tensileParticle;
  pd.shape = shape;
  pd.color = new b2ParticleColor(0, 0, 255, 0);
  var something = particleSystem.CreateParticleGroup(pd);
//var someting = particleSystem.CreateParticle(pd);
//console.log(something);
/*
var graphic = new PIXI.Graphics();
      graphic.lineStyle(0);
      graphic.boundsPadding = 20;
      graphic.beginFill(color, 1);
      graphic.drawCircle(0, 0, 3);
      graphic.endFill();
      graphic.cacheAsBitmap = false;
      graphic.filters = [ blurFilter ];
texture = app.renderer.generateTexture(graphic)
var sprite = new PIXI.Sprite(texture);
*/
//particles.push(something);

}

function resetParticles(){
  //var particles = world.getParticles();
//  var particles = world.particleSystems[0].GetPositionBuffer();
//var particles = liquidSimulation.getParticles();
var particles = this.world.particleSystems[0].GetPositionBuffer();
console.log("x");
console.log(particles[0]);
console.log(particles[1]);
console.log(typeof particles[0]);
//console.log(particles[0].x);
  for(var i = 0; i < particles.length; i+=2){
    var p = particles[i];
    if(p < -2){
      //x?
      particles[i] = 1;
      //y?
      paricles[i+1] = 3;
    }

    //this works!!!
    //p.DestroyParticles();

  //  console.log(p);
/*
    if(p < 0){
    //  p.position.set(1, 3.7);
    //stage.remove(p, p.index);

    }else if(p.position.x < -0.25){
      ///  stage.remove(p, p.index);
    //    p.position.set(1, 3.7);
    }
    */

  }
}
