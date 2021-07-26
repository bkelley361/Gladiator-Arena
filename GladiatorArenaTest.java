import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class GladiatorArenaTest {
    @Test
    public void QueueTest1() {
        Queue<String> q = new Queue<>();
        assertEquals(0, q.size());
    }
    @Test
    public void QueueTest2() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        assertEquals(1, q.size());
    }
    @Test
    public void QueueTest3() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        q.enqueue("H");
        assertEquals(2, q.size());
        assertEquals("J", q.peek());
    }
    @Test
    public void QueueTest4() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        q.enqueue("H");
        assertEquals(2, q.size());
        q.dequeue();
        assertEquals("H", q.peek());
    }

    @Test
    public void QueueTest5() {
        Queue<String> q = new Queue<>();
        assertEquals(true, q.isEmpty());
    }

    @Test
    public void QueueTest6() {
        Queue<String> q = new Queue<>();
        q.enqueue("J");
        q.enqueue("H");
        assertEquals(2, q.size());
        q.dequeue();
        q.dequeue();
        assertEquals(0, q.size());
    }

    @Test
    public void EnqueueMonster() {
        Queue<Creature> q = new Queue<>();
        Creature m = new Creature(10, 10);
        q.enqueue(m);
        assertEquals(m, q.peek());
    }

    @Test
    public void CreatureHealthTest1() {
        Creature m = new Creature(20, 10);
        m.hurt(10);
        assertEquals(10, m.health);
    }

    @Test
    public void CreatureHealthTest2() {
        Creature m = new Creature(20, 10);
        m.hurt(1);
        assertEquals(19, m.health);
    }

    @Test
    public void CreatureHealthTest3() {
        Creature m = new Creature(20, 10);
        m.hurt(0);
        assertEquals(20, m.health);
    }

    @Test
    public void CreatureHealthTest4() {
        Creature m = new Creature(20, 10);
        m.hurt(20);
        assertEquals(0, m.health);
    }

    @Test
    public void CreatureHealthTest5() {
        Creature m = new Creature(20, 10);
        m.hurt(21);
        assertEquals(-1, m.health);
    }

    @Test
    public void CreatureIsDeadTest1() {
        Creature m = new Creature(20, 10);
        m.hurt(20);
        assertEquals(true, m.isDead());
    }

    @Test
    public void CreatureIsDeadTest2() {
        Creature m = new Creature(20, 10);
        m.hurt(21);
        assertEquals(true, m.isDead());
    }

    @Test
    public void CreatureIsDeadTest3() {
        Creature m = new Creature(20, 10);
        m.hurt(19);
        assertEquals(false, m.isDead());
    }

    @Test
    public void CreatureGiveSpellTest1() {
        Creature m = new Creature(20, 10);
        SpellType s = SpellType.Frost;
        m.giveSpell(s);
        assertEquals(SpellType.Frost, m.type);
    }

    @Test
    public void CreatureGiveSpellTest2() {
        Creature m = new Creature(20, 10);
        SpellType s = SpellType.Fire;
        m.giveSpell(s);
        assertEquals(SpellType.Fire, m.type);
    }

    @Test
    public void CreatureGiveSpellTest3() {
        Creature m = new Creature(20, 10);
        SpellType s = SpellType.Fire;
        m.giveSpell(s);
        assertEquals(SpellType.Fire, m.type);
    }

    @Test
    public void CreatureAttack1() {
        Creature m = new Creature(20, 10);
        for (int i = 0; i < 50; i++) {
            assertTrue(m.attack() < 10 && m.attack() > -1);
        }
    }

    @Test
    public void CreatureFrozen1() {
        Creature m = new Creature(20, 10);
        m.frozenTimer = 2;
        assertTrue(m.isFrozen());
    }

    @Test
    public void CreatureFrozen2() {
        Creature m = new Creature(20, 10);
        m.frozenTimer = 2;
        m.decFreezeTimer();
        assertTrue(m.isFrozen());
    }

    @Test
    public void CreatureFrozen3() {
        Creature m = new Creature(20, 10);
        m.frozenTimer = 2;
        m.decFreezeTimer();
        m.decFreezeTimer();
        assertFalse(m.isFrozen());
    }

    @Test
    public void CreatureFire1() {
        Creature m = new Creature(20, 10);
        m.fireTimer = 3;
        m.decFireTimer();
        m.decFireTimer();
        m.decFireTimer();
        assertFalse(m.isOnFire());
    }

    @Test
    public void CreatureFire2() {
        Creature m = new Creature(20, 10);
        m.fireTimer = 3;
        m.decFireTimer();
        m.decFireTimer();
        assertTrue(m.isOnFire());
    }

    @Test
    public void PlayerXP1() {
        Player p = new Player(20, 10, 0);
        p.addXP(89);
        assertEquals(89, p.XP);
    }

    @Test
    public void PlayerXP2() {
        Player p = new Player(20, 10, 0);
        p.addXP(100);
        assertTrue(p.canLevelUp());
    }

    @Test
    public void PlayerXP3() {
        Player p = new Player(20, 10, 0);
        p.addXP(101);
        assertTrue(p.canLevelUp());
    }

    @Test
    public void PlayerXP4() {
        Player p = new Player(20, 10, 0);
        p.addXP(99);
        assertFalse(p.canLevelUp());
    }

    @Test
    public void PlayerLevelUp1() {
        Player p = new Player(20, 10, 0);
        p.addXP(100);
        p.levelUp(SpellType.Frost);
        assertEquals(2, p.level);
        assertEquals(30, p.health);
        assertEquals(15, p.strength);
        assertEquals(0, p.XP);
    }

    @Test
    public void PlayerLevelUp2() {
        Player p = new Player(20, 10, 0);
        p.addXP(150);
        p.levelUp(SpellType.Frost);
        assertEquals(2, p.level);
        assertEquals(30, p.health);
        assertEquals(15, p.strength);
        assertEquals(50, p.XP);
    }
}