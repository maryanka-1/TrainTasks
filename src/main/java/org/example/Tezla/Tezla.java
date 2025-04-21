package org.example.Tezla;

class Tezla extends Automobile {
    protected double autoPilotMaxSpeed;
    protected double autoPilotAcceleration;

    public Tezla() {
        autoPilotMaxSpeed = 60;
        autoPilotAcceleration = 11;
    }

    public void accelerateByAutopilot() {

        speed += autoPilotAcceleration;
        if(speed>autoPilotMaxSpeed) {
            speed = autoPilotMaxSpeed;
        }
    }

    @Override
    public void brake() {
        speed -= brakingSpeed;
        if(speed<0) {
            speed = 0;
        }
    }

    @Override
    public void accelerate() {
        speed += acceleration;
        if(speed>maxSpeed) {
            speed = maxSpeed;
        }
    }
}
