import java.util.Scanner;
import java.util.Random;

class Main {
    public static void main(String[] args) 
    {
        int fivePity = 0;
        double fiveChanceInitial = 0.006; //standard 0.006
        double fiveChance = fiveChanceInitial;

        int fourPity = 0;
        double fourChanceInitial = 0.051; //standard 0.051
        double fourChance = fourChanceInitial;
        int wishAmount = 1;

        Scanner sc = new Scanner (System.in);
        Random rd = new Random();
        
        String[] threeStar = {"Cool Steel", "Harbinger of Dawn", "Skyrider Sword",
        "Bloodtainted Greatsword", "Debate Club", "Ferrous Shadow",
        "Black Tassel",
        "Raven Bow", "Sharpshooter's Oath", "Slingshot",
        "Emerald Orb", "Magic Guide", "Thrilling Tales of Dragon Slayers"};
        
        String[] fourStar = {"Favonius Sword", "Lion's Roar", "The Flute","Sacrificial Sword",
        "Ferrous Shadow", "Bloodtainted Greatsword", "Sacrificial Greatsword", "Favonius Greatsword", "Rainslasher", "The Bell",
        "Favonius Lance", "Dragon's Bane",
        "Sacrificial Bow", "Rust", "The Stringless", "Favonius Warbow",
        "Eye of Perception", "The Widsith", "Sacrificial Fragments", "Favonius Codex"};
        
        String[] fourStarC = {"Amber", "Diona", "Fischl", "Barbara", "Lisa", "Ningguang", "Sucrose", "Beidou", "Chongyun", "Noelle", "Razor", "Xinyan", "Xiangling", "Bennett", "Kaeya", "Xingqiu"};
        
        String[] fiveStar = {"Aquila Favonia", "Skyward Blade", "Skyward Pride", "Wolf's Gravestone", "Primordial Jade Winged-Spear", "Skyward Spine", "Amos' Bow", "Skyward Harp", "Lost Prayer to the Sacred Winds", "Skyward Atlas"};
    
        String[] fiveStarC = {"Mona", "Diluc", "Jean", "Qiqi", "Keqing"};

        while(true) 
        {
            System.out.println("\n[1] single pull");
            System.out.println("[2] ten pull\n");

            int input = sc.nextInt();
            if(input == 1)
            {
            wishAmount = 1;
            }
            else if(input == 2)
            {
            wishAmount = 10;
            }
            else
            {
                break;
            }

            for(int i = 0; i < wishAmount; i++)
            {
                //code for calculating five star pity
                if(fivePity <= 76)
                {
                    fiveChance += 0.0002;                   
                }
                else if (fivePity > 76)
                {
                    fiveChance += 0.06;
                }

                if(fourPity > 7)
                {
                    fourChance += 0.30;
                }

                //code for wishes
                if(rd.nextDouble() <= fiveChance || fivePity == 89)
                {
                    if (rd.nextDouble() > 0.5)
                    {
                       System.out.print(fiveStarC[rd.nextInt(fiveStarC.length)]);
                    }
                    else
                    {
                        System.out.print(fiveStar[rd.nextInt(fiveStar.length)]);
                    }
                    System.out.printf(" (%.2f%% Chance) (5*)----\n", (fiveChance * 100));
                    fivePity = 0;
                    fiveChance = fiveChanceInitial;
                    fourPity += 1;
                    continue;
                }
                else if(rd.nextDouble() <= fourChance || fourPity == 9)
                {
                    if (rd.nextDouble() > 0.5)
                    {
                        System.out.println(fourStarC[rd.nextInt(fourStarC.length)] + "(4*)----");
                    }
                    else
                    {
                        System.out.println(fourStar[rd.nextInt(fourStar.length)] + "(4*)----");
                    }
                    fourPity = 0;
                    fourChance = fourChanceInitial;
                    fivePity += 1;
                    continue;
                    
                }                 
                else 
                {
                    System.out.println(threeStar[rd.nextInt(threeStar.length)] + "(3*)");
                }
                fourPity += 1;
                fivePity += 1;
            }

            System.out.println("\nPity = " + fivePity);
        }
    }
}
