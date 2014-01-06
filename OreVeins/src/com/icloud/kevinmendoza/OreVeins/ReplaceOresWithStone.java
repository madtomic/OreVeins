package com.icloud.kevinmendoza.OreVeins;

import java.util.Random;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class ReplaceOresWithStone extends BlockPopulator 
{
	
	@Override
	public void populate(World world, Random rand, Chunk chunk) 
	{
		Block block;
		for (int x = 0; x < 16; x++)
		{
			for (int y = 1; y < 128; y++)
			{
				for (int z = 0; z < 16; z++)
				{//getBlock(x, y, z).getType().compareTo(Material.STONE)==0
					block = chunk.getBlock(x, y, z);
					if (block.getType().compareTo(Material.COAL_ORE)==0 
							|| block.getType().compareTo(Material.IRON_ORE)==0 
							|| block.getType().compareTo(Material.GOLD_ORE)==0 
							|| block.getType().compareTo(Material.LAPIS_ORE)==0 
							|| block.getType().compareTo(Material.REDSTONE_ORE)==0 
							|| block.getType().compareTo(Material.DIAMOND_ORE)==0 
							|| block.getType().compareTo(Material.EMERALD_ORE)==0)
					{
						block.setType(Material.STONE);
					}
				}
			}
		}
		String[][][] draw =	VeinChunkReadWrite.readChunks(LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ()), false);
		if(draw !=null)
		{
			//DebugLogger.console("Drawing veins in existing chunk");
			VeinDrawer.drawVein(draw, chunk);
			
		}
		VeinChunkReadWrite.deleteChunkInfo(LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ()),false);
		String[][][] oldOres = VeinChunkReadWrite.readChunks(LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ()),true);
		if(oldOres !=null)
		{
			//DebugLogger.console("Drawing veins in existing chunk");
			VeinDrawer.drawVein(draw, chunk);
		}
		VeinChunkReadWrite.deleteChunkInfo(LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ()),true);
	}
	
}