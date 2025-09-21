package com.example.prac_work_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class MainActivity2 : ComponentActivity(), View.OnClickListener {

    lateinit var btnRes : Button
    lateinit var btnBack : Button
    lateinit var etHg : EditText // Вхідні дані Hg
    lateinit var etCg : EditText // Cg
    lateinit var etSg : EditText // Sg
    lateinit var etOg : EditText // Og
    lateinit var etVg : EditText // Vg
    lateinit var etWg : EditText // Wg
    lateinit var etAg : EditText // Ag
    lateinit var etX : EditText // Нижча теплота згоряння горючої маси мазуту, МДж/кг

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btnRes = findViewById(R.id.btn_res)
        btnBack = findViewById(R.id.btn_back)
        etHg = findViewById(R.id.et_hg)
        etCg = findViewById(R.id.et_cg)
        etSg = findViewById(R.id.et_sg)
        etOg = findViewById(R.id.et_og)
        etVg = findViewById(R.id.et_vg)
        etWg = findViewById(R.id.et_wg)
        etAg = findViewById(R.id.et_ag)
        etX = findViewById(R.id.et_x)
        btnRes.setOnClickListener(this)
        btnBack.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        val hg = etHg.text.toString().toDouble()
        val cg = etCg.text.toString().toDouble()
        val sg = etSg.text.toString().toDouble()
        val og = etOg.text.toString().toDouble()
        val vg = etVg.text.toString().toDouble()
        val wg = etWg.text.toString().toDouble()
        val ag = etAg.text.toString().toDouble()
        val x = etX.text.toString().toDouble()

        when(v?.id){
            R.id.btn_res -> {
                // Розрахунок коефіцієнтів
                val solut1 = (100 - wg - ag) / 100
                val solut2 = (100 - wg) / 100

                // Розрахунок складу робочої маси мазуту
                val cg2 = cg * solut1
                val hg2 = hg * solut1
                val og2 = og * solut1
                val sg2 = sg * solut1
                val ag2 = ag * solut2
                val vg2 = vg * solut2

                // Розрахунок нижчої теплоти згорання мазуту на робочу масу
                val q = x * ((100 - wg - ag2) / 100) - 0.025 * ag

                fun Double.round(decimals: Int): Double {
                    return "%.${decimals}f".format(this).toDouble()
                }

                val resultText = """
                    
                    Склад робочої маси мазуту становитиме:
                    Hp = ${hg2.round(2)} %
                    Cp = ${cg2.round(2)} %
                    Sp = ${sg2.round(2)} %
                    Op = ${og2.round(2)} %
                    Vp = ${vg2.round(2)} %
                    Ap = ${ag2.round(2)} %
                
                    Нижча теплота згоряння мазуту та робочої маси за заданим складом компонентів палива становить: 
                    ${q.round(2)} %
                """.trimIndent()
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("RESULT_TEXT", resultText)
                startActivity(intent)
            }
            R.id.btn_back -> {
                val intent = Intent(this, HomePage::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
