clear;
clc;
length=27;%mm
diameter=16; %mm
capacitance=220; %F
price=9.10696; %USD
desiredTimeOfCharge=3600*2; % We decided 2 hours of charge time was reasonable
maxMotorOutput=350 % Motor output was calculated elsewhere attached as appendix 1 of report


flatTerrainCost = 400*.25;

totalCapacitancePerBank= 4*capacitance;
maxMotorOuput=650;

% Took a measurements from a bike frame, Assuming that the capacitors were
% going to go into the tubes of the bike frame, Bike frame was assumed to
% have an inner square length of roughy 32mm
% Tube size is 32mmx32mm Which means 4 capacitors placed like this
% x x
% x x

xOfFrame= 570; % If bike frame is trangular shaped, the x is the part from handlebar to seat.
yOfFrame= 420; % Y is from pedals to seat.
diagOfFrame= sqrt(570^2+420^2); % diag is from pedals to handlebars.


numberOfCapsInX=floor(xOfFrame/length);
numberOfCapsInY=floor(yOfFrame/length);
numberOfCapsInDiag=floor(diagOfFrame/length);
totalNumberOfCaps=numberOfCapsInX+numberOfCapsInY+numberOfCapsInDiag;
capacitanceInX=totalCapacitancePerBank*numberOfCapsInX;
capacitanceInY=totalCapacitancePerBank*numberOfCapsInY;
capacitanceIndiag=totalCapacitancePerBank*numberOfCapsInDiag;
TotalCapacitance=capacitanceIndiag+capacitanceInX+capacitanceInY;
energyInX=(capacitanceInX*3.8^2)/2;
energyInY=(capacitanceInY*3.8^2)/2;
energyInDiag=(capacitanceIndiag*3.8^2)/2;
totalEnergyStorage= energyInX+energyInY+energyInDiag;

fprintf("The vertical component of the bike frame willl store %f kilojoules of energy \n",energyInY/1000)
fprintf("The horizontal component of the bike frame willl store %f kilojoules of energy \n",energyInX/1000)
fprintf("The diagonal component of the bike will store %f kilojoules of energy \n",energyInDiag/1000)
fprintf("In total the bike has the ability to store %f kilojoules of energy \n",totalEnergyStorage/1000)
fprintf("This will cost %f to implement\n",totalNumberOfCaps*price);
fprintf("This can power the bike for %f seconds at max power \n",totalEnergyStorage/maxMotorOutput)
fprintf("Charger must be rated for %f watts \n", totalEnergyStorage/desiredTimeOfCharge);
fprintf("Can aid cyclist on Flat terrain for %f seconds \n",totalEnergyStorage/flatTerrainCost);
fprintf("There are %f capacitors per bike \n",totalNumberOfCaps);




