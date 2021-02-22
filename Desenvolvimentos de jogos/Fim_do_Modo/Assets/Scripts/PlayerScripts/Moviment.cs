using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Moviment : MonoBehaviour
{

    public Animator animations;
    float timer = 0;

    // Start is called before the first frame update
    void Start()
    {
        
    }

    // Update is called once per frame
    void Update()
    {
        timer+= Time.deltaTime;
        if(timer > 10f){
            animations.Play("PlayerAnimation2");
        }else if(timer > 0){
            animations.Play("PlayerAnimation1");
        }
    }
}
