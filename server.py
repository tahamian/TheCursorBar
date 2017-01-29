'''
    Simple socket server using threads
'''
 
import socket
import sys
from thread import *
import keyboard
import os
import subprocess
 
HOST = ''   # Symbolic name meaning all available interfaces
PORT = 8888 # Arbitrary non-privileged port
 
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print 'Socket created'
 
#Bind socket to local host and port
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print 'Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
    sys.exit()
     
print 'Socket bind complete'
 
#Start listening on socket
s.listen(10)
print 'Socket now listening'
 
#Function for handling connections. This will be used to create threads
def clientthread(conn):
    #Sending message to connected client
    conn.send('Welcome to the server. Type something and hit enter\n') #send only takes string
    #infinite loop so that function do not terminate and thread do not end.
    while True:
         
        #Receiving from client
        data = conn.recv(1024)
        print(data)
        if (data.startswith("ctrl+c")):
            keyboard.press('ctrl')
            keyboard.press_and_release('c')
            keyboard.release('ctrl')

        elif (data.startswith("ctrl+v")):
            keyboard.press('ctrl')
            keyboard.press_and_release('v')
            keyboard.release('ctrl')

        elif (data.startswith("ctrl+esc")):
            keyboard.press('ctrl')
            keyboard.press_and_release('escape')
            keyboard.release('ctrl')        

        elif (data.startswith("ctrl+a")):
            keyboard.press('ctrl')
            keyboard.press_and_release('a')
            keyboard.release('ctrl')

        elif (data.startswith("ctrl+z")):
            keyboard.press('ctrl')
            keyboard.press_and_release('z')
            keyboard.release('ctrl')

        elif (data.startswith("ctrl+y")):
            keyboard.press('ctrl')
            keyboard.press_and_release('y')
            keyboard.release('ctrl')

        elif (data.startswith("notepad")):
            subprocess.call(['notepad.exe'])

        elif (data.startswith("explorer")):
            subprocess.call(['explorer.exe'])

        elif (data.startswith("snip")):
            subprocess.call(['snippingtool.exe'])

        elif (data.startswith("cmd")):
            subprocess.call(['cmd.exe'])


        
        reply = 'OK...' + data
        if not data: 
            break
     
        conn.sendall(reply)
     
    #came out of loop
    conn.close()
 
#now keep talking with the client
while 1:
    #wait to accept a connection - blocking call
    conn, addr = s.accept()
    print 'Connected with ' + addr[0] + ':' + str(addr[1])
    #start new thread takes 1st argument as a function name to be run, second is the tuple of arguments to the function.
    start_new_thread(clientthread ,(conn,))
 
s.close()