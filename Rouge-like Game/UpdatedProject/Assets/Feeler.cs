using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Tilemaps;

public class Feeler : MonoBehaviour
{
    public Tilemap myMap;
    Vector3Int tilePosition;

    void OnTriggerEnter2D(Collider2D other) {
        if(other.CompareTag("SpawnPoint")){
            //Tile at feeler positon + 4 delete and replace it
            int x = (int)other.transform.position.x;
            int y = (int)other.transform.position.y;
            int z = (int)other.transform.position.z;
            tilePosition = new Vector3Int(x, y, z);
            myMap.SetTile(tilePosition, null);
        }
    }
}
