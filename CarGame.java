PImage car;
PImage bomb;
PImage spikeball;

// Car
int car_x = 135;
int car_y = 450;

// Bomb
int bomb_x = 300;
int bomb_y = 0;
int bombspeed = 0;

//// Spikeball
int spike_x = 300;
int spike_y = 0;
int sbspeed = 0;

int score = 0;

boolean showIntro = true;

void setup() {
  size(927, 600);
  background(76, 154, 0);
  
  car = loadImage("images/car.png");
  bomb = loadImage("images/bomb.png");
  spikeball = loadImage("images/spikeball.png");
  
  frameRate(60);
}

void car() {
  image(car, car_x, car_y);
}

void drawIntro() {
  background(76, 154, 0);
  int buttonWidth = 200;
  int buttonHeight = 60;
  int centerX = width / 2 - buttonWidth / 2;
  int startY = height / 2 - 100;
  textSize(50);
  fill(255);
  textAlign(CENTER);
  text("Select Difficulty", width / 2, height / 4);
  
  fill(255, 0, 0);
  rect(centerX, startY, buttonWidth, buttonHeight);
  fill(255);
  textSize(30);
  text("Easy", width / 2, startY + 40);
  
  fill(255, 165, 0);
  rect(centerX, startY + 100, buttonWidth, buttonHeight);
  fill(255);
  text("Medium", width / 2, startY + 140);
  
  fill(0, 0, 255);
  rect(centerX, startY + 200, buttonWidth, buttonHeight);
  fill(255);
  text("Hard", width / 2, startY + 240);
}

void draw() {
  if (showIntro) {
    drawIntro();
  } else {
    background(76, 154, 0);
    noStroke();
    fill(128, 128, 128);
    rect(125, 0, 700, 600);
    car();
    image(bomb, bomb_x, bomb_y);
    image(spikeball, spike_x, spike_y);
  
    bomb_y = bomb_y + bombspeed;
    spike_y = spike_y + sbspeed;
  
    if (bomb_y > height) {        
      bomb_x = int(random(135, 790));
      bomb_y = -100;
      score += 1;
      
      while (abs(bomb_x - spike_x) < 50) {
        bomb_x = int(random(135, 790));
      }
    } 
    
    if (spike_y > height) {
      spike_x = int(random(135, 790));
      spike_y = -100;
      
      while (abs(spike_x - bomb_x) < 50) {
        spike_x = int(random(135, 790));
      }
    }
  
    if (collision(car, car_x, car_y, bomb, bomb_x, bomb_y)) {
      exit();
    }
    
    if (collision(spikeball, car_x, car_y, spikeball, spike_x, spike_y)) {
      exit();
    }
  
    if (car_x < 125) {
      exit();
    }
  
    if (car_x > 770) {
      exit(); 
    }
    
    textSize(30);
    fill(0);
    text("Score: " + score, 58, 50);
    }  
}

void mousePressed() {
  if (showIntro) {
    if (mouseX > 300 && mouseX < 500) {
      if (mouseY > 200 && mouseY < 260) {
        bombspeed = 4;
        sbspeed = 4;
        showIntro = false;
      } else if (mouseY > 300 && mouseY < 360) {
        bombspeed = 8;
        sbspeed = 8;
        showIntro = false;
      } else if (mouseY > 400 && mouseY < 460) {
        bombspeed = 12;
        sbspeed = 12;
        showIntro = false;
      }
    }
  }
}

void keyPressed() {
  if(key == CODED){
    if (keyCode == LEFT) {
      if (car_x > 17) {
        car_x -= 50;
      }
    }
    
    if (keyCode == RIGHT) {
      if (car_x < 950 - 120) {
        car_x += 50;
      }
    }
  }
}

boolean collision(PImage a, int ax, int ay, PImage b, int bx, int by){
 if ((ax > bx && ax < (bx + b.width)) || ((ax + a.width) > bx && (ax + a.width) < (bx + b.width)))
  {
   if ((  ay > by && ay < (by + b.height)) || ((ay + a.height) > by && (ay + a.height) < (by + b.height)) )
      {
        return true;
      }
   }
   return false;
}