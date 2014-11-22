package eu.pi1.bplcc_app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends Activity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
	Fragment f;
	switch(position) {
		case 0: f = new OinserFragment(); break;
		case 1: f = new OinserBerFragment(); break;
        case 2: f = new ZwoerFragment(); break;
        case 3: f = new DruierFragment(); break;
		default: f = new OinserFragment(); break;
	}
        //fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
	fragmentManager.beginTransaction().replace(R.id.container, f).commit();
    }

    public void onSectionAttached(int number) {
        /*switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
*/
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }
    
    public static class OinserFragment extends Fragment {

        private int oldValue;

    	private View rootView;
    	private Button button;
        private TextView textview;

        private NumberPicker stroboAnPicker;
        private NumberPicker stroboAusPicker;
        private Switch stroboSwitch;

        //private static String PIURLLED = "http://192.168.1.123/433/light.php?";
	    private static String ARDUINOLEDURL = "http://192.168.1.10/";
        
    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_oins, container, false);
            textview = (TextView) rootView.findViewById(R.id.ArduinoValue);

            addListenerOnButton();
            return rootView;
        }
    	
    	public void addListenerOnButton() {
    		
    		button = (Button) rootView.findViewById(R.id.button_min);
    		button.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
                    new UrlContent(ARDUINOLEDURL+String.valueOf("1"));
                    textview.setText(String.valueOf("1"));
                    oldValue = 1;
                }
    		});
    		
    		button = (Button) rootView.findViewById(R.id.button_an);
    		button.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
                    new UrlContent(ARDUINOLEDURL+String.valueOf("72"));
                    textview.setText(String.valueOf("72"));
                    oldValue = 72;
    			}
    		});
    		
    		button = (Button) rootView.findViewById(R.id.button_aus);
    		button.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
                    new UrlContent(ARDUINOLEDURL+String.valueOf("0"));
                    textview.setText(String.valueOf("0"));
                    oldValue = 0;
    			}
    		});
    		
    		button = (Button) rootView.findViewById(R.id.button_max);
    		button.setOnClickListener(new OnClickListener() {
    			@Override
    			public void onClick(View arg0) {
                    new UrlContent(ARDUINOLEDURL+String.valueOf("255"));
                    textview.setText(String.valueOf("255"));
                    oldValue = 255;
    			}
    		});
    		
    		SeekBar sk = (SeekBar) rootView.findViewById(R.id.seekbar);
    		sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				int value = seekBar.getProgress();
                    new UrlContent(ARDUINOLEDURL+String.valueOf(value));
                    textview.setText(String.valueOf(value));
                    oldValue = value;
    			}
    			
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {
    				// TODO Auto-generated method stub
    				
    			}
    			
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress,
    					boolean fromUser) {
    				int value = seekBar.getProgress();
                    new UrlContent(ARDUINOLEDURL+String.valueOf(value));
                    textview.setText(String.valueOf(value));
                    oldValue = value;
    			}
    		});

            stroboAnPicker = (NumberPicker) rootView.findViewById(R.id.strobo_an);
            stroboAusPicker = (NumberPicker) rootView.findViewById(R.id.strobo_aus);

            stroboAnPicker.setMinValue(0);
            stroboAnPicker.setMaxValue(1000);
            stroboAnPicker.setValue(10);
            stroboAnPicker.setWrapSelectorWheel(false);

            stroboAusPicker.setMinValue(0);
            stroboAusPicker.setMaxValue(1000);
            stroboAusPicker.setValue(10);
            stroboAusPicker.setWrapSelectorWheel(false);

            stroboSwitch = (Switch) rootView.findViewById(R.id.strobo_switch);

            stroboSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        String strobo_usr = ARDUINOLEDURL+"s/"+stroboAnPicker.getValue()+"/"+stroboAusPicker.getValue();
                        new UrlContent(strobo_usr);
                        textview.setText(String.valueOf("strobo an:"+stroboAnPicker.getValue()+" aus:"+stroboAusPicker.getValue()));
                    }
                    else
                    {
                        new UrlContent(ARDUINOLEDURL+String.valueOf(oldValue));
                        textview.setText(String.valueOf(oldValue));
                        oldValue = oldValue;
                    }
                }
            });



    	}
    }

    public static class OinserBerFragment extends Fragment {

        private View rootView;
        private WebView web;
        private Button button;
        private static String PIURLRELAIS = "http://192.168.1.123/relais/index.php";
        private static String WOLURL = "http://192.168.1.123/wol/wol.php?device=";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_oins_b, container, false);
            web = (WebView) rootView.findViewById(R.id.webViewLight);
            web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            web.getSettings().setAppCacheEnabled(false);
            addListenerOnButton();
            return rootView;
        }

        public void addListenerOnButton() {


            // RELAIS

            button = (Button) rootView.findViewById(R.id.button_r1_1);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    web.loadUrl(PIURLRELAIS+"?pin=1&mode=0");
                }
            });

            button = (Button) rootView.findViewById(R.id.button_r1_0);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    web.loadUrl(PIURLRELAIS+"?pin=1&mode=1");
                }
            });

            button = (Button) rootView.findViewById(R.id.button_r2_1);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    web.loadUrl(PIURLRELAIS+"?pin=2&mode=0");
                }
            });

            button = (Button) rootView.findViewById(R.id.button_r2_0);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    web.loadUrl(PIURLRELAIS+"?pin=2&mode=1");
                }
            });

            // WOL
            button = (Button) rootView.findViewById(R.id.button_computer_on);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    web.loadUrl(WOLURL+"Pc");
                }
            });

            button = (Button) rootView.findViewById(R.id.button_server_on);
            button.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    web.loadUrl(WOLURL+"Server");
                }
            });
        }
    }
    
    public static class ZwoerFragment extends Fragment {

    	private View rootView;
    	private WebView web;
    	private SeekBar bar1;
    	private SeekBar bar2;
    	private SeekBar bar3;
    	private SeekBar bar4;
    	private SeekBar bar5;
    	private SeekBar bar6;
    	private SeekBar bar7;
    	private SeekBar bar8;
    	
        private static String PIURLLED = "http://192.168.1.225/pwm/index.php?";
        private enum Colors { WHITE, RED, GREEN ,BLUE };
    	
    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_zwo, container, false);
            
            web = (WebView) rootView.findViewById(R.id.webViewBillard);
            web.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            web.getSettings().setAppCacheEnabled(false);
            addListenerOnButton();
            
            return rootView;
        }
    	
    	private   void addListenerOnButton() {
    		
    		bar1 = (SeekBar) rootView.findViewById(R.id.SeekBar01);
    		bar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(true, Colors.WHITE, bar1.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(true, Colors.WHITE, bar1.getProgress());
    			}
    		});
    		
    		bar2 = (SeekBar) rootView.findViewById(R.id.SeekBar02);
    		bar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(false, Colors.WHITE, bar2.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(false, Colors.WHITE, bar2.getProgress());
    			}
    		});
    		
    		bar3 = (SeekBar) rootView.findViewById(R.id.SeekBar03);
    		bar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(true, Colors.RED, bar3.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(true, Colors.RED, bar3.getProgress());
    			}
    		});
    		
    		bar4 = (SeekBar) rootView.findViewById(R.id.SeekBar04);
    		bar4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(true, Colors.GREEN, bar4.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(true, Colors.GREEN, bar4.getProgress());
    			}
    		});
    		
    		bar5 = (SeekBar) rootView.findViewById(R.id.SeekBar05);
    		bar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(true, Colors.BLUE, bar5.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(true, Colors.BLUE, bar5.getProgress());
    			}
    		});
    		
    		bar6 = (SeekBar) rootView.findViewById(R.id.SeekBar06);
    		bar6.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(false, Colors.RED, bar6.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(false, Colors.RED, bar6.getProgress());
    			}
    		});
    		
    		bar7 = (SeekBar) rootView.findViewById(R.id.SeekBar07);
    		bar7.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(false, Colors.GREEN, bar7.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(false, Colors.GREEN, bar7.getProgress());
    			}
    		});
    		
    		bar8 = (SeekBar) rootView.findViewById(R.id.SeekBar08);
    		bar8.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
    			@Override
    			public void onStopTrackingTouch(SeekBar seekBar) {
    				setLight(false, Colors.BLUE, bar8.getProgress());
    			}
    			@Override
    			public void onStartTrackingTouch(SeekBar seekBar) {}
    			@Override
    			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
    				setLight(false, Colors.BLUE, bar8.getProgress());
    			}
    		});
    	}
    	
    	private void setLight(boolean inner, Colors color, int value) {
    		String urlcomplet = "";
    		if(inner)
    			urlcomplet += "pos=i";
    		else
    			urlcomplet += "pos=a";
    		switch (color) {
    		case WHITE:
    			urlcomplet += "&color=w";
    			break;
    		case RED:
    			urlcomplet += "&color=r";
    			break;
    		case GREEN:
    			urlcomplet += "&color=g";
    			break;
    		case BLUE:
    			urlcomplet += "&color=b";
    			break;
    		default:
    			urlcomplet += "&color=w";
    			break;
    		}
    		urlcomplet += "&value="+value;
    		//((TextView)findViewById(R.id.debug)).setText(urlcomplet);
    		web.loadUrl(PIURLLED+urlcomplet);
    	}
    }

    public static class DruierFragment extends Fragment {

    	private View rootView;
        
    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            rootView = inflater.inflate(R.layout.fragment_drui, container, false);
            return rootView;
        }
    	
    }

}
