using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class EnterDoor : MonoBehaviour
{
    public int Direction;
    public float cameraSpeed = .125f; 
    private int rand;
    private RoomTemplates templates;
    Vector3 currCameraPosition;

    private void Start() {
        templates = GameObject.FindGameObjectWithTag("Rooms").GetComponent<RoomTemplates>();
        currCameraPosition = GameObject.Find("Main Camera").transform.position;
    }

    private void OnTriggerEnter2D(Collider2D other) {
        if(other.gameObject.tag == "Player"){
            if(Direction == 1){
                GameObject.Find("Main Camera").transform.position = new Vector3(currCameraPosition.x, currCameraPosition.y + 8, currCameraPosition.z);
            } else if(Direction == 2){
                GameObject.Find("Main Camera").transform.position = new Vector3(currCameraPosition.x, currCameraPosition.y - 8, currCameraPosition.z);
            } else if(Direction == 3){
                GameObject.Find("Main Camera").transform.position = new Vector3(currCameraPosition.x + 14, currCameraPosition.y, currCameraPosition.z);
            } else if(Direction == 4){
                GameObject.Find("Main Camera").transform.position = new Vector3(currCameraPosition.x - 14, currCameraPosition.y, currCameraPosition.z);
            } else{
                Debug.Log("This shouldnt be seen, a spawnpoint is missing a direction");
            }
        }
    }
}
