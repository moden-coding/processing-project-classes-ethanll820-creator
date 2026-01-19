import processing.core.PApplet;
import java.util.ArrayList;

public class NPCSpawner {

    private PApplet canvas;
    private Hunter hunter;

    public NPCSpawner(PApplet canvas, Hunter hunter) {
        this.canvas = canvas;
        this.hunter = hunter;
    }

    // Creates NPCs for a given level and adds them to the NPCs list
    public void spawnLevel(int level, ArrayList<NPC> NPCs) {
        int npcCount;
        int npcSpeed;

        if (level == 1) {
            npcCount = 5;
            npcSpeed = 2;
            spawnNPCs(npcCount, npcSpeed, 100, 1900, 100, 1500, NPCs);
        } else if (level == 2) {
            npcCount = 10;
            npcSpeed = 3;
            spawnNPCs(npcCount, npcSpeed, 100, 1900, 100, 1500, NPCs);
        } else if (level == 3) {
            npcCount = 15;
            npcSpeed = 3;
            spawnNPCs(npcCount, npcSpeed, 50, 1950, 50, 1550, NPCs);
        } else if (level == 4) {
            npcCount = 20;
            npcSpeed = 4;
            spawnNPCs(npcCount, npcSpeed, 50, 1950, 50, 1550, NPCs);
        } else { // level 5+
            npcCount = 25;
            npcSpeed = 5;
            spawnNPCs(npcCount, npcSpeed, 0, 2000, 0, 1600, NPCs);
        }
    }

    // Helper method to create NPCs at random positions, avoiding the hunter
    private void spawnNPCs(int count, int speed, float minX, float maxX, float minY, float maxY, ArrayList<NPC> NPCs) {
        for (int i = 0; i < count; i++) {
            float x, y, dx, dy, dist;

            do {
                x = canvas.random(minX, maxX);
                y = canvas.random(minY, maxY);

                dx = x - hunter.getX();
                dy = y - hunter.getY();
                dist = PApplet.sqrt(dx * dx + dy * dy);

            } while (dist < 300); // keep NPC away from hunter

            NPCs.add(new NPC(x, y, 50, speed, canvas, hunter));
        }
    }
}
