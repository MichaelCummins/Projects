using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player : MonoBehaviour
{
    public float moveSpeed = 3f;
    public float spellDamage = 2f;
    public float range = 10f;
    public float resource = 20f;

    float velX;
    float velY;
    Rigidbody2D rigBody;
    public SpriteRenderer spriteRenderer;
    public Sprite spLeft, spRight, spUp, spDown, spTopLeft, spTopRight, spBotLeft, spBotRight;

    void Start(){
        rigBody = GetComponent<Rigidbody2D>();

    }


    void Update(){
        velX = Input.GetAxis("Horizontal");
        velY = Input.GetAxis("Vertical");
        //velY = rigBody.velocity.y;
        rigBody.velocity = new Vector2(velX * moveSpeed, velY * moveSpeed);

        if(Input.GetKey("w")){
           gameObject.GetComponent<SpriteRenderer>().sprite = spUp;
        }
        if(Input.GetKey("a")){
           gameObject.GetComponent<SpriteRenderer>().sprite = spLeft;
        }
        if(Input.GetKey("s")){
           gameObject.GetComponent<SpriteRenderer>().sprite = spDown;
        }
        if(Input.GetKey("d")){
           gameObject.GetComponent<SpriteRenderer>().sprite = spRight;
        }

        if(Input.GetKey("w") && Input.GetKey("a")){
            gameObject.GetComponent<SpriteRenderer>().sprite = spTopLeft;
        }
        
        if(Input.GetKey("w") && Input.GetKey("d")){
                gameObject.GetComponent<SpriteRenderer>().sprite = spTopRight;
            }
        if(Input.GetKey("s") && Input.GetKey("a")){
                gameObject.GetComponent<SpriteRenderer>().sprite = spBotLeft;
            }
        if(Input.GetKey("s") && Input.GetKey("d")){
                gameObject.GetComponent<SpriteRenderer>().sprite = spBotRight;
            }
    }
}
