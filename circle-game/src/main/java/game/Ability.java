package game;

import java.util.ArrayList;
import java.util.List;

public class Ability {

  private Character c;
  private double fullCooldown, cooldown;
  private double fullDuration, duration;
  private boolean active = false;

  private List<Projectile> projectiles = new ArrayList<>();

  public Ability(Character c, double fullCooldown, double cooldown) {
    this.c = c;
    this.fullCooldown = fullCooldown;
    this.cooldown = cooldown;
  }

  public Ability(Character c, double fullCooldown, double cooldown,
                 double fullDuration, double duration) {
    this.c = c;
    this.fullCooldown = fullCooldown;
    this.cooldown = cooldown;
    this.fullDuration = fullDuration;
    this.duration = duration;
  }

  public void update() {
    if (cooldown > 0) {
      reduceCooldown();
    }
    if (active) {
      reduceDuration();
      if (duration == 0) {
        c.getColor().setAlpha(255);
        active = false;
        resetDuration();
      }
      for (Projectile p : projectiles) {
        p.update();
      }
    }
  }

  public void resetCooldown() { cooldown = fullCooldown; }

  public void reduceCooldown() { cooldown--; }

  public void resetDuration() { duration = fullDuration; }

  public void reduceDuration() { duration--; }

  public void addProjectile(Projectile p) { projectiles.add(p); }

  public double getFullCooldown() { return fullCooldown; }
  public void setFullCooldown(double fullCooldown) {
    this.fullCooldown = fullCooldown;
  }

  public double getCooldown() { return cooldown; }
  public void setCooldown(double cooldown) { this.cooldown = cooldown; }

  public double getFullDuration() { return fullDuration; }
  public void setFullDuration(double fullDuration) {
    this.fullDuration = fullDuration;
  }

  public double getDuration() { return duration; }
  public void setDuration(double duration) { this.duration = duration; }

  public boolean isActive() { return active; }

  public void setActive(boolean active) { this.active = active; }

  public List<Projectile> getProjectiles() { return projectiles; }

  public void setProjectiles(List<Projectile> projectiles) {
    this.projectiles = projectiles;
  }
}
