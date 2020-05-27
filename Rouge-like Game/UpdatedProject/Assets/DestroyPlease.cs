using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class DestroyPlease : MonoBehaviour
{
    void OnDestroy () {
     Destroy(transform.parent.gameObject);
 }
}
