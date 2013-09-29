package utils;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.OutputStream;
import java.util.Enumeration;

public class Arduino 
{
	private static Arduino	arduino;
	
	private OutputStream 	output;
    private SerialPort 		serialPort;
    private final String 	PORT = "COM11";
    
    private static final int TIMEOUT = 2000; //Milisegundos
    private static final int DATA_RATE = 9600;
    
    private Arduino()
    {
    	CommPortIdentifier puertoID = null;
        Enumeration<?> puertoEnum = CommPortIdentifier.getPortIdentifiers();
        
        while(puertoEnum.hasMoreElements())
        {
            CommPortIdentifier actualPuertoID = (CommPortIdentifier) puertoEnum.nextElement();
            if(PORT.equals(actualPuertoID.getName()))
            {
                puertoID = actualPuertoID;
                break;
            }
        }
        
        if(puertoID == null)
        {
            System.out.println("No se puede conectar al puerto");
        }
        
        try{
            serialPort = (SerialPort) puertoID.open(this.getClass().getName(), TIMEOUT);            
            serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            output = serialPort.getOutputStream();
        } catch(Exception e){
            System.out.println(e.getMessage());        
        }
    }
    
    public void sendData(String data)
    {
    	try{
            arduino.getOutput().write(data.getBytes());
            System.out.println("Enviado: "+data.toString());
        } catch(Exception e){
            System.out.println("Error sending data: " + e.getMessage());
        }
    }

	public OutputStream getOutput() 
	{
		return output;
	}
	
	public static Arduino getInstance()
	{
		if(arduino == null)
		{
			arduino = new Arduino();
			System.out.println("Se crea el arduino");
		}
		return arduino;
	}
	
	public static void main(String [] args)
	{
		System.out.println("INICIADO");
		Arduino.getInstance().sendData("3");
		System.out.println("TERMINADO");
	}
}
